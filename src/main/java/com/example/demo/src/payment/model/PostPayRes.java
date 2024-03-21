package com.example.demo.src.payment.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostPayRes {
    private Long id;
    private String detail;
}
