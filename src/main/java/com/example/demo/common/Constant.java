package com.example.demo.common;

import com.example.demo.common.exceptions.BaseException;

import static com.example.demo.common.code.status.ErrorStatus.INVALID_PAGE;
import static com.example.demo.common.code.status.ErrorStatus.INVALID_SIZE;

public class Constant {
    public enum SocialLoginType{
        NONE,
        GOOGLE,
        KAKAO,
        NAVER
    }

    public static final String CREATE_AT = "createdAt";
    //구독 금액
    public static final int SUBSCRIPTION_AMOUNT = 100;

    public static void validPage(Integer page, Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size < 0) {
            throw new BaseException(INVALID_SIZE);
        }
    }
}

