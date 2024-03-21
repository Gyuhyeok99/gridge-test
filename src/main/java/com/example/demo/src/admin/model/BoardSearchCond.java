package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.validation.annotation.LocalDateTimeForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSearchCond {

    @Schema(description = "아이디(null 가능)", example = "gyuhyeok99")
    private String username;
    @Schema(description = "게시글 생성 날짜(null 가능)", example = "20240320")
    @LocalDateTimeForm
    private String signupDate;
    @Schema(description = "게시글 상태(null 가능)", example = "ACTIVE")
    private State state;
}
