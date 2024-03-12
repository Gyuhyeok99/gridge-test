package com.example.demo.src.auth;


import com.example.demo.src.auth.model.PostLoginRes;
import com.example.demo.src.auth.model.PostRefreshRes;
import com.example.demo.src.auth.model.PostUserReq;
import com.example.demo.src.auth.model.PostUserRes;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.demo.src.user.entity.Role.USER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthConverter {

    public static User toUser(PostUserReq postUserReq){
        return User.builder()
                .phoneNumber(postUserReq.getPhoneNumber())
                .username(postUserReq.getUsername())
                .birth(LocalDate.parse(postUserReq.getBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .password(postUserReq.getPassword())
                .name(postUserReq.getName())
                .isOAuth(postUserReq.isOAuth())
                .termsAgreed(postUserReq.isTermsAgreed())
                .termsAgreedDate(postUserReq.getTermsAgreedDate())
                .role(USER)
                .build();
    }

    public static PostUserRes toPostUserRes(Long userId, String accessToken, String refreshToken){
        return PostUserRes.builder()
                .id(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public static PostLoginRes toPostLoginRes(Long userId, String accessToken, String refreshToken){
        return PostLoginRes.builder()
                .id(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static PostRefreshRes toPostRefreshRes(String accessToken){
        return PostRefreshRes.builder()
                .accessToken(accessToken)
                .build();
    }
}
