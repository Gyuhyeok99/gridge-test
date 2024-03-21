package com.example.demo.src.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardImageReq {

    @Schema(description = "이미지 url", example = "https://~~")
    @NotNull
    private String imageUrl;

    @Schema(description = "이미지 순서", example = "1")
    @NotNull
    private Integer imageOrder;
}
