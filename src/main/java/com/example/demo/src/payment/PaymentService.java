package com.example.demo.src.payment;


import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.payment.entity.Payment;
import com.example.demo.src.payment.entity.enums.PaymentStatus;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.entity.User;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.example.demo.common.Constant.SUBSCRIPTION_AMOUNT;
import static com.example.demo.common.code.status.ErrorStatus.*;
import static com.example.demo.src.payment.entity.enums.PaymentStatus.OK;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    // 결제 검증 로직

    private final IamportClient iamportClient;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional(noRollbackFor = BaseException.class)
    public PostPayRes payValidate(User user, PostPayReq postPayReq) {
        Payment payment;
        try {
            // 결제 검증 로직 먼저 실행
            iamportClient.paymentByImpUid(postPayReq.getImpUid());
            if(user.getSubscriptionAgreed()) {
                iamportClient.cancelPaymentByImpUid(new CancelData(postPayReq.getImpUid(), true));
                // 결제 취소 후, PaymentHistory에 실패 기록 저장
                payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "이미 구독한 유저");
                paymentRepository.save(payment);
                throw new BaseException(ALEADY_SUBSCRIBED_USER);
            }
            // 금액 검증
            if(postPayReq.getAmount() != SUBSCRIPTION_AMOUNT) {
                // 결제 취소 로직 추가
                iamportClient.cancelPaymentByImpUid(new CancelData(postPayReq.getImpUid(), true));
                // 결제 취소 후, PaymentHistory에 실패 기록 저장
                payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "금액 불일치");
                paymentRepository.save(payment);
                throw new BaseException(NOT_MATCH_PAYMENT_AMOUNT);
            }
            // 금액 검증 후 결제 성공
            payment = PaymentConverter.toPayment(OK, user, postPayReq, "결제 성공");
            user.updateSubscriptionAgreed(true);
            log.info("user.getSubscriptionAgreed() : " + user.getSubscriptionAgreed());
            paymentRepository.save(payment);
            userRepository.save(user);
        } catch (IamportResponseException e) {
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "결제 정보 없음");
            paymentRepository.save(payment);
            throw new BaseException(NOT_FIND_PAYMENT);
        } catch (IOException e) {
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, postPayReq, "시스템 오류");
            paymentRepository.save(payment);
            throw new BaseException(ERROR_PAYMENT);
        }
        return PaymentConverter.toPostPayRes(payment);
    }

    @Transactional(noRollbackFor = BaseException.class)

    public void cancelPayment(User user, Long paymentUid) {
        Payment findPayment = paymentRepository.findByIdAndUserAndPaymentStatus(paymentUid, user, OK).orElseThrow(() -> new BaseException(NOT_FIND_PAYMENT));
        Payment payment;
        try {
            iamportClient.cancelPaymentByImpUid(new CancelData(findPayment.getPaymentUid(), false));
            user.updateSubscriptionAgreed(false);
            payment = PaymentConverter.toPayment(PaymentStatus.CANCEL, user, findPayment.getPaymentUid(), "결제 취소 성공");
            paymentRepository.save(payment);
        } catch (IamportResponseException e) {
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, findPayment.getPaymentUid(), "결제 정보 없음");
            paymentRepository.save(payment);
            throw new BaseException(NOT_FIND_PAYMENT);
        } catch (IOException e) {
            payment = PaymentConverter.toPayment(PaymentStatus.FAIL, user, findPayment.getPaymentUid(), "시스템 오류");
            paymentRepository.save(payment);
            throw new BaseException(ERROR_PAYMENT);
        }
    }
}
