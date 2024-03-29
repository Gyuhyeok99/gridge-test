package com.example.demo.src.auth;

import com.example.demo.common.Constant;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.oauth.kakao.KakaoService;
import com.example.demo.common.oauth.kakao.model.GetKakaoRes;
import com.example.demo.src.auth.model.*;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.utils.RedisProvider;
import com.example.demo.utils.SmsUtil;
import com.example.demo.utils.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Random;

import static com.example.demo.common.code.status.ErrorStatus.*;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
import static com.example.demo.common.entity.BaseEntity.State.SUSPENDED;
import static com.example.demo.utils.jwt.JwtProvider.HEADER_AUTHORIZATION;
import static com.example.demo.utils.jwt.JwtProvider.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisProvider redisProvider;
    private final SmsUtil smsUtil;
    private final KakaoService kakaoService;
    //POST
    public PostUserRes createUser(PostUserReq postUserReq) {
        //중복 체크
        userRepository.findByUsernameAndState(postUserReq.getUsername(), ACTIVE)
                .ifPresent(user -> { throw new BaseException(POST_USERS_EXISTS_EMAIL); });
        postUserReq.setPassword(passwordEncoder.encode(postUserReq.getPassword()));
        User user = AuthConverter.toUser(postUserReq);
        User savedUser = userRepository.save(user);
        String accessToken = jwtProvider.generateToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);
        saveUserToken(savedUser, refreshToken);
        return AuthConverter.toPostUserRes(savedUser.getId(), accessToken, refreshToken);
    }

    public PostLoginRes login(PostLoginReq postLoginReq) {
        userRepository.findByUsernameAndState(postLoginReq.getUsername(), SUSPENDED)
                .ifPresent(user -> {throw new BaseException(SUSPENDED_USER);});
        User user = userRepository.findByUsernameAndState(postLoginReq.getUsername(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_USER));
        if(user.getTermsAgreed().equals(Boolean.FALSE)) {
            throw new BaseException(EXPIRED_TERMS_AGREED);
        }
        try{
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(postLoginReq.getUsername(), postLoginReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(postLoginReq.getUsername(), postLoginReq.getPassword()));

        String accessToken = jwtProvider.generateToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);
        saveUserToken(user, refreshToken);
        user.updateLastLoginAt();
        log.info("Class: AuthController Method: login" + " is called by User: " + user.getUsername());
        return AuthConverter.toPostLoginRes(user, accessToken, refreshToken);
    }

    public Boolean checkAuthNum(PostFindCheckReq postFindCheckReq) {
        userRepository.findByUsernameAndState(postFindCheckReq.getUsername(), SUSPENDED)
                .ifPresent(user -> {throw new BaseException(SUSPENDED_USER);});
        String storedAuthNum = redisProvider.getValueOps(postFindCheckReq.getPhoneNumber());
        if (storedAuthNum != null && storedAuthNum.equals(postFindCheckReq.getVerificationCode())) {
            log.info("휴대폰 인증 성공");
            return true;
        } else {
            log.error("휴대폰 인증 실패");
            throw new BaseException(PHONE_AUTH_FAIL);
        }
    }

    public String joinMail(PostFindPhoneReq postFindPhoneReq) {
        // 휴대폰에 대한 기존 인증번호가 있는지 확인하고, 있다면 삭제
        String oldVerificationCode = redisProvider.getValueOps(postFindPhoneReq.getPhoneNumber());
        if (oldVerificationCode != null) {
            log.info("기존 인증번호 삭제 : {}", oldVerificationCode);
            redisProvider.deleteValueOps(postFindPhoneReq.getPhoneNumber());
        }
        sendSmsToFindEmail(postFindPhoneReq);
        return "인증 번호 문자 전송 완료";
    }
    public String changePassword(PatchChangePasswordReq patchChangePasswordReq) {
        String storedAuthNum = redisProvider.getValueOps(patchChangePasswordReq.getPhoneNumber());
        if (storedAuthNum == null || !storedAuthNum.equals(patchChangePasswordReq.getVerificationCode())) {
            log.error("휴대폰 인증 실패");
            throw new BaseException(PHONE_AUTH_FAIL);
        }
        if(!patchChangePasswordReq.getPassword().equals(patchChangePasswordReq.getPasswordCheck())){
            throw new BaseException(PASSWORD_NOT_MATCH_CONFIRM);
        }

        User user = userRepository.findByUsernameAndState(patchChangePasswordReq.getUsername(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_USER));
        user.setPassword(passwordEncoder.encode(patchChangePasswordReq.getPassword()));
        log.info("비밀번호 변경 완료 : {}", user.getPassword());
        userRepository.save(user);
        return "비밀번호 변경 완료";
    }

    @Transactional
    public PostSocialRes socialLogin(Constant.SocialLoginType socialLoginType, String authorizationCode) {
        switch (socialLoginType){
            case KAKAO: {
                GetKakaoRes getKakaoRes = kakaoService.getUserInfo(kakaoService.getAccessToken(authorizationCode));
                User user = userRepository.findByUsernameAndState(getKakaoRes.getId(), ACTIVE)
                        .orElseThrow(() -> new BaseException(NOT_FIND_USER));
                String accessToken = jwtProvider.generateToken(user);
                String refreshToken = jwtProvider.generateRefreshToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, refreshToken);
                return AuthConverter.toPostSocialRes(user, accessToken, refreshToken);
            }
            case GOOGLE: {
                //TODO 구글 로그인
            }
            case NAVER: {
                //TODO 네이버 로그인
            }
            default:{
                throw new BaseException(INVALID_OAUTH_TYPE);
            }

        }
    }

    public PostRefreshRes refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX))
            return null;
        refreshToken = authHeader.substring(7);
        username = jwtProvider.extractUsername(refreshToken);
        if (username != null) {
            User user = userRepository.findByUsernameAndState(username, ACTIVE)
                    .orElseThrow(() -> new BaseException(NOT_FIND_USER));
            if (jwtProvider.isTokenValid(refreshToken, user)) {
                String accessToken = jwtProvider.generateToken(user);
                return AuthConverter.toPostRefreshRes(accessToken);
            }
        }
        return null;
    }

    private void saveUserToken(User user, String refreshToken) {
        redisProvider.setValueOps(user.getUsername(), refreshToken);
        redisProvider.expireValues(user.getUsername());
    }

    private void revokeAllUserTokens(User user) {
        redisProvider.deleteValueOps(user.getUsername());
    }

    private void sendSmsToFindEmail(PostFindPhoneReq postFindPhoneReq) {
        String username = postFindPhoneReq.getUsername();
        String phoneNum = postFindPhoneReq.getPhoneNumber().replace("+82", "0");
        phoneNum = phoneNum.replaceAll("-", "");
        log.info("phoneNum : {}", phoneNum);
        userRepository.findByUsernameAndPhoneNumberAndState(username, postFindPhoneReq.getPhoneNumber(), ACTIVE)
                .orElseThrow(()-> new BaseException(NOT_FIND_USER));
        int verificationCode = makeRandomNumber();
        log.info("인증번호 : {}", verificationCode);
        smsUtil.sendOne(phoneNum, verificationCode);
        redisProvider.setDataExpire(postFindPhoneReq.getPhoneNumber(), Integer.toString(verificationCode), 60*5L);
    }

    //임의의 6자리 양수를 반환
    private int makeRandomNumber() {
        return new Random().ints(100000,999999)
                .findFirst()
                .getAsInt();
    }


}
