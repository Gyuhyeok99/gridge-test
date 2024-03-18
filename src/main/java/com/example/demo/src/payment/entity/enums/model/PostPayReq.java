package com.example.demo.src.payment.entity.enums.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostPayReq {

    private String impUid;
    private String merchantUid;
    private int amount;
}
