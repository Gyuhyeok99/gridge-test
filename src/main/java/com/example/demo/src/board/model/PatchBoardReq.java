package com.example.demo.src.board.model;

import com.example.demo.common.validation.annotation.MaxImageSize;
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
public class PatchBoardReq {

    @Schema(description = "게시글 내용 수정", example = "내용 수정 ~~🙄")
    @NotNull
    @Size(max = 2200)
    private String content;

    @MaxImageSize
    private List<PatchBoardImageReq> boardImageReqs;
}
