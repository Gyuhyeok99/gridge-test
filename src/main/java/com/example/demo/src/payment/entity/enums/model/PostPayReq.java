package com.example.demo.src.payment.entity.enums.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostPayReq {

    private String impUid;
    private String merchantUid;
    private BigDecimal amount;
}
