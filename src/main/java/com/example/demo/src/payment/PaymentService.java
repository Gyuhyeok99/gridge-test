package com.example.demo.src.payment;


import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.payment.entity.Payment;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.entity.User;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.example.demo.common.Constant.SUBSCRIPTION_AMOUNT;
import static com.example.demo.common.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    // 결제 검증 로직

    private final IamportClient iamportClient;
    private final PaymentRepository paymentRepository;

    @Transactional(noRollbackFor = BaseException.class)
    public PostPayRes payValidate(User user, PostPayReq postPayReq) {
        Payment payment;
        // 금액 검증 실패 시, PaymentHistory 저장
        if(postPayReq.getAmount() != SUBSCRIPTION_AMOUNT) {
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "금액 불일치");
            paymentRepository.save(payment);
            throw new BaseException(NOT_MATCH_PAYMENT_AMOUNT);
        }
        try {
            // 결제 검증 로직
            iamportClient.paymentByImpUid(postPayReq.getImpUid());
            // 결제 성공 시, PaymentHistory 저장
            payment = PaymentConverter.toPayment(PaymentStatus.OK, user, postPayReq, "결제 성공");
            paymentRepository.save(payment);
        } catch (IamportResponseException e) {
            // 결제 찾기 실패 시, PaymentHistory 저장
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "결제 정보 없음");
            paymentRepository.save(payment);
            throw new BaseException(NOT_FIND_PAYMENT);
        } catch (IOException e) {
            // 결제 시스템 오류 시, PaymentHistory 저장
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "시스템 오류");
            paymentRepository.save(payment);
            throw new BaseException(ERROR_PAYMENT);
        }
        return PaymentConverter.toPostPayRes(payment);
    }
}
