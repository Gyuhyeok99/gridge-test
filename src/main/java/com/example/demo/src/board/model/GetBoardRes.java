package com.example.demo.src.board.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import com.example.demo.src.comment.model.GetCommentRes;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardRes {
    @NotNull
    private Long id;
    @UsernameForm
    private String username;
    @NotNull
    @Size(max = 2200)
    private String content;
    private List<GetBoardImageRes> boardImageRes;
    @NotNull
    private Integer likesCount;
    @NotNull
    private Integer commentsCount;
    @NotNull
    private String createdAt;
    private List<GetCommentRes> commentsRes;

}
