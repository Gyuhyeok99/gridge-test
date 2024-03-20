package com.example.demo.src.board.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikesRes {

    @NotNull
    private Long boardId;
    @NotNull
    private Long likes;
}
