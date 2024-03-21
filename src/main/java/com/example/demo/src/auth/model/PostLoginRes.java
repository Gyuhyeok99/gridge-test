package com.example.demo.src.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLoginRes {

    @NotNull
    private Long id;
    private boolean subscriptionAgreed;
    private boolean termsAgreed;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}
