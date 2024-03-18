package com.example.demo.src.payment;

import com.example.demo.src.payment.entity.Payment;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentConverter {

    public static Payment toPayment(PaymentStatus paymentStatus, User user, PostPayReq postPayReq, String detail) {
        return Payment.builder()
                .paymentStatus(paymentStatus)
                .user(user)
                .paymentUid(postPayReq.getImpUid())
                .detail(detail)
                .build();
    }

    public static PostPayRes toPostPayRes(Payment payment) {
        return PostPayRes.builder()
                .id(payment.getId())
                .detail(payment.getDetail())
                .build();
    }
}
