package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.validation.annotation.LocalDateTimeForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSearchCond {
    @Schema(description = "이름(null 가능)", example = "혁규")
    private String name;
    @Schema(description = "아이디(null 가능)", example = "gyuhyeok99")
    private String username;
    @Schema(description = "가입 날짜(null 가능)", example = "20240320")
    @LocalDateTimeForm
    private String signupDate;
    @Schema(description = "상태(null 가능)", example = "ACTIVE")
    private State state;
}
