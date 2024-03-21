package com.example.demo.src.auth.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRefreshRes {
    @NotNull
    private String accessToken;
}
