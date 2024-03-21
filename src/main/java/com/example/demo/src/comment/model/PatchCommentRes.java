package com.example.demo.src.comment.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchCommentRes {

    @NotNull
    private Long id;
}
