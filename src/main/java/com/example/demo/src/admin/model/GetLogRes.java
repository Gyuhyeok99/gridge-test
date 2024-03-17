package com.example.demo.src.admin.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetLogRes {

    private Long id;
    private String methodName;
    private String userName;
    private LocalDateTime createdAt;

}
