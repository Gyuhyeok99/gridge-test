package com.example.demo.src.auth;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.auth.model.*;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.utils.RedisProvider;
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


import static com.example.demo.common.code.status.ErrorStatus.*;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
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
        //String refreshToken = jwtProvider.generateRefreshToken(user);
        return AuthConverter.toPostUserRes(savedUser.getId(), accessToken, refreshToken);
    }

    public PostLoginRes login(PostLoginReq postLoginReq) {
        User user = userRepository.findByUsernameAndState(postLoginReq.getUsername(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_USER));
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
        return AuthConverter.toPostLoginRes(user.getId(), accessToken, refreshToken);
    }



    public PostUserRes createOAuthUser(User user) {
        User saveUser = userRepository.save(user);

        // JWT 발급
        String accessToken = jwtProvider.generateToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);
        saveUserToken(saveUser, refreshToken);
        return AuthConverter.toPostUserRes(saveUser.getId(), accessToken, refreshToken);

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
        //key는 사용자 이메일과 토큰 발급 시간으로 구성 // 추후에 발급 시간이 아닌 기기로 구분하는 거로 수정해야함
        //redisService.setValueOps(user.getEmail() + ":" + issuedAt, refreshToken);
        log.info("user : {}", user.getUsername());
        redisProvider.setValueOps(user.getUsername(), refreshToken);
        redisProvider.expireValues(user.getUsername());
    }

    private void revokeAllUserTokens(User user) {
        redisProvider.deleteValueOps(user.getUsername());
    }
}
