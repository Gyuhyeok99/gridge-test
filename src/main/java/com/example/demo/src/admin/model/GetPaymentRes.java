package com.example.demo.src.admin.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GetPaymentRes {
    @NotNull
    private Long id;
    @NotNull
    private String detail;
    private PaymentStatus paymentStatus;
    @NotNull
    private String paymentUid;
    @UsernameForm
    private String username;
    private LocalDateTime createdAt;
}
