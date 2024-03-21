package com.example.demo.src.auth.model;

import com.example.demo.common.validation.annotation.PhoneForm;
import com.example.demo.common.validation.annotation.UsernameForm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchChangePasswordReq {

    @Schema(description = "아이디", example = "gyuhyeok99")
    @NotNull
    @Size(max = 20)
    @UsernameForm
    private String username;

    @Schema(description = "휴대폰 번호", example = "+82-10-1234-5678")
    @NotNull
    @Size(max = 20)
    @PhoneForm
    private String phoneNumber;

    @Schema(description = "비밀번호", example = "123456")
    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @Schema(description = "비밀번호 확인", example = "123456")
    @NotNull
    @Size(min = 6, max = 20)
    private String passwordCheck;

    @Schema(description = "인증번호", example = "123456")
    @NotNull
    private String verificationCode;
}
