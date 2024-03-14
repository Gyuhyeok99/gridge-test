package com.example.demo.src.board.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikesRes {

    private Long boardId;
    private Long likes;
}
