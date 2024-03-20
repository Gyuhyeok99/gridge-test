package com.example.demo.src.auth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSocialRes {

    private Long id;
    private String accessToken;
    private String refreshToken;
    private boolean subscriptionAgreed;
    private boolean termsAgreed;
}
