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
    private String accessToken;
    private String refreshToken;
}
