package com.example.demo.src.auth.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRefreshRes {
    private String accessToken;
}
