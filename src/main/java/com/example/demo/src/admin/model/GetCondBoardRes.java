package com.example.demo.src.admin.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetCondBoardRes {

    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private Long likesCount;
    private Long commentsCount;

    @QueryProjection
    public GetCondBoardRes(Long id, String username, String content, LocalDateTime createdAt, Long likesCount, Long commentsCount) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.likesCount = likesCount;
        this.commentsCount = commentsCount;
    }
}
