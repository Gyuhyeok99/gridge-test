package com.example.demo.src.comment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchCommentReq {

    @NotNull
    @Size(max = 2200)
    private String content;
}
