package com.example.demo.src.payment;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.payment.entity.Payment;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import com.example.demo.src.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIdAndUserAndPaymentStatus(Long id, User user, PaymentStatus paymentStatus);

    Page<Payment> findByState(State state, Pageable pageable);
}
