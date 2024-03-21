package com.example.demo.src.comment.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCommentRes {

    @NotNull
    private Long id;
    @NotNull
    @Size(max = 2200)
    private String content;
    @NotNull
    private String createdAt;
    @UsernameForm
    private String username;
}
