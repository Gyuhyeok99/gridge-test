package com.example.demo.src.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSocialRes {

    @NotNull
    private Long id;
    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
    private boolean subscriptionAgreed;
    private boolean termsAgreed;
}
