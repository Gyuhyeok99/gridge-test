package com.example.demo.src.payment;

import com.example.demo.common.log.Trace;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.entity.User;
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
@Trace
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validate")
    public BaseResponse<PostPayRes> payValidate(@AuthenticationPrincipal User user, @RequestBody PostPayReq postPayReq) {
        return BaseResponse.onSuccess(paymentService.payValidate(user, postPayReq));
    }
}