package com.example.demo.src.payment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostPayReq {

    @Schema(description = "아임포트 결제 고유번호", example = "imp_1234567890")
    private String impUid;
    @Schema(description = "주문번호", example = "merchant_1234567890")
    private String merchantUid;
    @Schema(description = "결제 금액", example = "9900")
    private int amount;
}
