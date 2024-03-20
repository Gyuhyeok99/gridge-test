package com.example.demo.src.auth.model;


import com.example.demo.common.validation.annotation.UsernameForm;
import com.example.demo.common.validation.annotation.UsernameUnique;
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
public class PostCheckUsernameReq {

    @Schema(description = "아이디", example = "gyuhyeok99")
    @NotNull
    @Size(max = 20)
    @UsernameForm
    @UsernameUnique
    private String username;
}
