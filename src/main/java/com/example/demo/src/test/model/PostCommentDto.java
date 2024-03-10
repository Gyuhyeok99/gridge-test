package com.example.demo.src.test.model;

import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDto {
    private Long memoId;
    @NotBlank(message = "코멘트를 입력해주세요.")
    private String comment;

    public Comment toEntity(Memo memo) {
        return Comment.builder()
                .comment(this.comment)
                .memo(memo)
                .build();
    }
}
