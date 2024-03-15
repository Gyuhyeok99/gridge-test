package com.example.demo.src.admin.model;

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
    private String reporter;
    private String username;
    private LocalDateTime createAt;
}
