package com.example.demo.src.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserRes {
    @NotNull
    private Long id;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

}
