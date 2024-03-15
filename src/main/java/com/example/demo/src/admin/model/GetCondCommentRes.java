package com.example.demo.src.admin.model;

import com.querydsl.core.annotations.QueryProjection;
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

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String username;

    @QueryProjection
    public GetCondCommentRes(Long id, String content, LocalDateTime createdAt, String username) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.username = username;
    }
}
