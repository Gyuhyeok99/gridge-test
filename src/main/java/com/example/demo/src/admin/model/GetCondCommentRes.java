package com.example.demo.src.admin.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class GetCondCommentRes {

    @NotNull
    private Long id;
    @NotNull
    @Size(max = 2200)
    private String content;
    private LocalDateTime createdAt;
    @UsernameForm
    private String username;

    @QueryProjection
    public GetCondCommentRes(Long id, String content, LocalDateTime createdAt, String username) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.username = username;
    }
}
