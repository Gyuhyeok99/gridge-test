package com.example.demo.src.comment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchCommentReq {

    @Schema(description = "댓글 내용", example = "댓글 수정~~🙄")
    @NotNull
    @Size(max = 2200)
    private String content;
}
