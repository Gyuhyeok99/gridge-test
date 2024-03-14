package com.example.demo.src.board.model;

import com.example.demo.src.comment.model.GetCommentRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardRes {
    private Long id;
    private String username;
    private String content;
    private List<GetBoardImageRes> boardImageRes;
    private Integer likesCount;
    private Integer commentsCount;
    private String createdAt;
    private List<GetCommentRes> commentsRes;

}
