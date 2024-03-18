package com.example.demo.src.admin.model;

import com.example.demo.src.payment.entity.enums.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetPaymentRes {
    private Long id;
    private String detail;
    private PaymentStatus paymentStatus;
    private String paymentUid;
    private String username;
    private LocalDateTime createdAt;
}
