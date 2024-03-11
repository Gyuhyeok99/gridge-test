package com.example.demo.src.auth;


import com.example.demo.src.auth.model.PostLoginRes;
import com.example.demo.src.auth.model.PostUserReq;
import com.example.demo.src.auth.model.PostUserRes;
import com.example.demo.src.user.entity.User;

import static com.example.demo.src.user.entity.Role.USER;

public class AuthConverter {

    public static User toUser(PostUserReq postUserReq){
        return User.builder()
                .phoneNumber(postUserReq.getPhoneNumber())
                .userId(postUserReq.getUserId())
                .birth(postUserReq.getBirth())
                .password(postUserReq.getPassword())
                .name(postUserReq.getName())
                .isOAuth(postUserReq.isOAuth())
                .termsAgreed(postUserReq.isTermsAgreed())
                .termsAgreedDate(postUserReq.getTermsAgreedDate())
                .role(USER)
                .build();
    }

    public static PostUserRes toPostUserRes(Long userId, String accessToken){
        return PostUserRes.builder()
                .id(userId)
                .accessToken(accessToken)
                .build();
    }


    public static PostLoginRes toPostLoginRes(Long userId, String accessToken) {
        return PostLoginRes.builder()
                .id(userId)
                .accessToken(accessToken)
                .build();
    }
}
