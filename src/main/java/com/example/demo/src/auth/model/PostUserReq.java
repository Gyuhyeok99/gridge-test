package com.example.demo.src.auth.model;

import com.example.demo.common.validation.annotation.*;
import com.example.demo.src.user.entity.User;
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
    @NotNull
    @Size(max = 20)
    @PhoneForm
    @PhoneUnique
    private String phoneNumber;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    @UsernameForm
    @UsernameUnique
    private String username;

    @NotNull
    @LocalDateForm
    private String birth;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    private boolean isOAuth;

    @MustBeTrue
    private boolean termsAgreed;

    @NotNull
    @LocalDateForm
    private String termsAgreedDate;


}
