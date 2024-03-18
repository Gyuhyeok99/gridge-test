package com.example.demo.src.payment;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@Slf4j
@Tag(name = "payment controller", description = "결제 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validate")
    @Operation(summary = "결제 검증 API", description = "결제 정보를 받아 결제 검증을 하는 API입니다.")
    public BaseResponse<PostPayRes> payValidate(@AuthenticationPrincipal User user, @RequestBody PostPayReq postPayReq) {
        return BaseResponse.onSuccess(paymentService.payValidate(user, postPayReq));
    }

    @PostMapping("/cancel/{paymentUid}")
    @Operation(summary = "결제 취소 API", description = "결제 정보를 받야 결제를 취소하는 API입니다.")
    public BaseResponse<String> cancelPayment(@AuthenticationPrincipal User user, @PathVariable(name = "paymentUid") Long paymentUid) {
        paymentService.cancelPayment(user, paymentUid);
        return BaseResponse.onSuccess("결제 취소 성공");
    }
}