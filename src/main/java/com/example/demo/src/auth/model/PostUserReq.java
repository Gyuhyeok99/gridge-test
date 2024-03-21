package com.example.demo.src.auth.model;

import com.example.demo.common.Constant;
import com.example.demo.common.Constant.SocialLoginType;
import com.example.demo.common.validation.annotation.*;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {

    @Schema(description = "휴대폰 번호", example = "+82-10-1234-5678")
    @NotNull
    @Size(max = 20)
    @PhoneForm
    @PhoneUnique
    private String phoneNumber;

    @Schema(description = "이름", example = "혁규")
    @NotNull
    @Size(max = 20)
    private String name;

    @Schema(description = "아이디", example = "gyuhyeok99")
    @NotNull
    @Size(max = 20)
    @UsernameForm
    @UsernameUnique
    private String username;

    @Schema(description = "생년월일", example = "1999-10-21")
    @NotNull
    @LocalDateForm
    private String birth;

    @Schema(description = "패스워드", example = "ghkdrbgur2")
    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Schema(description = "소셜가입 여부", example = "true")
    private boolean isOAuth;

    @Schema(description = "약관동의 여부", example = "true")
    @MustBeTrue
    private boolean termsAgreed;

    @Schema(description = "약관동의 날짜", example = "2024-03-20")
    @NotNull
    @LocalDateForm
    private String termsAgreedDate;

    @Schema(description = "소셜로그인 타입", example = "NONE")
    private SocialLoginType socialLoginType;


}
