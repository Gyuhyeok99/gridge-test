package com.example.demo.common.oauth.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KakaoUserInfo {

    String name;
    String phoneNumber;


}


