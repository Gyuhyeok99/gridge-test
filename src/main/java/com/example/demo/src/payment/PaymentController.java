package com.example.demo.src.payment;

import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.payment.entity.enums.model.PostPayReq;
import com.example.demo.src.payment.entity.enums.model.PostPayRes;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.SuccessStatus.PAYMENT_CANCEL_OK;
import static com.example.demo.common.code.status.SuccessStatus.PAYMENT_VALIDATE_OK;


@Slf4j
@Tag(name = "payment controller", description = "결제 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validate")
    @Operation(summary = "결제 검증 API", description = "결제 정보를 받아 결제 검증을 하는 API입니다.")
    @ApiResponse(responseCode = "PAYMENT2000",description = "결제 검증 성공")
    @ApiResponse(responseCode = "PAYMENT4000", description = "결제 정보를 찾을 수 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAYMENT4001", description = "결제에 실패하였습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAYMENT4002", description = "결제 금액이 일치하지 않습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAYMENT4003", description = "이미 구독한 유저입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<PostPayRes> payValidate(@AuthenticationPrincipal User user, @RequestBody PostPayReq postPayReq) {
        return BaseResponse.of(PAYMENT_VALIDATE_OK, paymentService.payValidate(user, postPayReq));
    }

    @PostMapping("/cancel/{paymentUid}")
    @Operation(summary = "결제 취소 API", description = "결제 정보를 받야 결제를 취소하는 API입니다.")
    @ApiResponse(responseCode = "PAYMENT2001",description = "결제 취소 성공")
    @ApiResponse(responseCode = "PAYMENT4000", description = "결제 정보를 찾을 수 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAYMENT4001", description = "결제에 실패하였습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "paymentUid", description = "paymentUid 번호")
    public BaseResponse<String> cancelPayment(@AuthenticationPrincipal User user, @PathVariable(name = "paymentUid") Long paymentUid) {
        paymentService.cancelPayment(user, paymentUid);
        return BaseResponse.of(PAYMENT_CANCEL_OK, "결제 취소 성공");
    }
}