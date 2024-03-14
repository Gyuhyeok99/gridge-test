package com.example.demo.src.board.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCommentRes {

    private Long id;
    private String content;
    private String createdAt;
    private String username;
}
