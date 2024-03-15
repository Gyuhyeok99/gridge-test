package com.example.demo.src.admin.model;

import com.example.demo.src.mapping.entity.enums.ReportContent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetReportRes {
    private Long id;
    private Long boardId;
    private ReportContent reportContent;
    private String reporter;
    private String username;
    private LocalDateTime createAt;
}
