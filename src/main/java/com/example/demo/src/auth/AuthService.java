package com.example.demo.src.auth;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.auth.model.PostLoginReq;
import com.example.demo.src.auth.model.PostLoginRes;
import com.example.demo.src.auth.model.PostUserReq;
import com.example.demo.src.auth.model.PostUserRes;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import com.example.demo.utils.jwt.JwtProvider;
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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    //POST
    public PostUserRes createUser(PostUserReq postUserReq) {
        //중복 체크
        userRepository.findByUserIdAndState(postUserReq.getUserId(), ACTIVE)
                .ifPresent(user -> { throw new BaseException(POST_USERS_EXISTS_EMAIL); });
        postUserReq.setPassword(passwordEncoder.encode(postUserReq.getPassword()));
        User user = AuthConverter.toUser(postUserReq);
        User savedUser = userRepository.save(user);
        String accessToken = jwtProvider.generateToken(user);
        //String refreshToken = jwtProvider.generateRefreshToken(user);
        return AuthConverter.toPostUserRes(savedUser.getId(), accessToken);
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) {
        User user = userRepository.findByUserIdAndState(postLoginReq.getUserId(), ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_USER));
        try{
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(postLoginReq.getUserId(), postLoginReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(postLoginReq.getUserId(), postLoginReq.getPassword()));

        String accessToken = jwtProvider.generateToken(user);
        return AuthConverter.toPostLoginRes(user.getId(), accessToken);

    }

    public PostUserRes createOAuthUser(User user) {
        User saveUser = userRepository.save(user);

        // JWT 발급
        String accessToken = jwtProvider.generateToken(user);
        return AuthConverter.toPostUserRes(saveUser.getId(), accessToken);

    }

}
