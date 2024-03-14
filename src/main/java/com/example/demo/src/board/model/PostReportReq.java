package com.example.demo.src.board.model;

import com.example.demo.src.mapping.entity.enums.ReportContent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReportReq {

    @NotNull
    private ReportContent reportContent;
}
