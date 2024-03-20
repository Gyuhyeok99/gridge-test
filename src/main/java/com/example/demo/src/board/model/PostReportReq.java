package com.example.demo.src.board.model;

import com.example.demo.src.mapping.entity.enums.ReportContent;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReportReq {

    @Schema(description = "신고 사유", example = "SPAM")
    @NotNull
    private ReportContent reportContent;
}
