package com.example.demo.src.board.model;


import com.example.demo.common.validation.annotation.MaxImageSize;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardReq {

    @Schema(description = "게시글 내용", example = "내용~~🙄")
    @NotNull
    @Size(max = 2200)
    private String content;

    @Schema(description = "이미지 url", example = "https://~~")
    @MaxImageSize
    private List<PostBoardImageReq> boardImageReqs;

}
