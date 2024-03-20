package com.example.demo.src.auth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLoginRes {

    private Long id;
    private boolean subscriptionAgreed;
    private boolean termsAgreed;
    private String accessToken;
    private String refreshToken;
}
