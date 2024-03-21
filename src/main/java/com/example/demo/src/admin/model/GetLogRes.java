package com.example.demo.src.admin.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetLogRes {

    @NotNull
    private Long id;
    @NotNull
    private String methodName;
    @NotNull
    private String userName;
    private LocalDateTime createdAt;

}
