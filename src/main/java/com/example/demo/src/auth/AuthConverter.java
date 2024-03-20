package com.example.demo.src.auth;


import com.example.demo.src.auth.model.*;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.demo.src.user.entity.enums.Role.USER;

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
                .termsAgreedDate(LocalDate.parse(postUserReq.getTermsAgreedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .lastLoginAt(LocalDateTime.now())
                .subscriptionAgreed(false)
                .socialLoginType(postUserReq.getSocialLoginType())
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


    public static PostLoginRes toPostLoginRes(User user, String accessToken, String refreshToken){
        return PostLoginRes.builder()
                .id(user.getId())
                .subscriptionAgreed(user.getSubscriptionAgreed())
                .termsAgreed(user.getTermsAgreed())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public static PostRefreshRes toPostRefreshRes(String accessToken){
        return PostRefreshRes.builder()
                .accessToken(accessToken)
                .build();
    }

    public static PostSocialRes toPostSocialRes(User user, String accessToken, String refreshToken){
        return PostSocialRes.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .subscriptionAgreed(user.getSubscriptionAgreed())
                .termsAgreed(user.getTermsAgreed())
                .refreshToken(refreshToken)
                .build();

    }
}
