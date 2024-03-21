package com.example.demo.src.admin.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetCondBoardRes {

    @NotNull
    private Long id;
    @UsernameForm
    private String username;
    @NotNull
    @Size(max = 2200)
    private String content;
    private LocalDateTime createdAt;
    @NotNull
    private Long likesCount;
    @NotNull
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
