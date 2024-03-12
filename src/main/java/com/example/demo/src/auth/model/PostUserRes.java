package com.example.demo.src.auth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserRes {
    private Long id;
    private String accessToken;
    private String refreshToken;

    public PostUserRes(Long id) {
        this.id = id;
    }
}
