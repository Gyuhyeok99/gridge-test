package com.example.demo.src.payment.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import com.example.demo.src.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    @Id // PK를 의미하는 어노테이션
    @Column(name = "payment_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String paymentUid; // 결제 고유 번호
    private String detail; // 결제 상세 정보


    public Payment(PaymentStatus paymentStatus, User user, String paymentUid, String detail) {
        this.paymentStatus = paymentStatus;
        this.user = user;
        this.paymentUid = paymentUid;
        this.detail = detail;
    }




}
