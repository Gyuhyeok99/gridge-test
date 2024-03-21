package com.example.demo.src.admin.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import com.example.demo.src.mapping.entity.enums.ReportContent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetReportRes {
    @NotNull
    private Long id;
    @NotNull
    private Long boardId;
    private ReportContent reportContent;
    @UsernameForm
    private String reporter;
    @UsernameForm
    private String username;
    private LocalDateTime createAt;
}
