package com.example.demo.src.auth;

import com.example.demo.common.Constant;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.auth.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "auth controller", description = "인증 필요 없는 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @ResponseBody
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 API",description = "회원 가입 정보를 받아 회원 정보를 생성합니다.")
    public BaseResponse<PostUserRes> createUser(@Validated @RequestBody PostUserReq postUserReq) {
        log.info("Class: AuthController Method: createUser" + " is called by User: " + postUserReq.getUsername());
        return BaseResponse.onSuccess(authService.createUser(postUserReq));
    }

    @ResponseBody
    @PostMapping("/sign-up/checked-phoneNumber")
    @Operation(summary = "회원가입 시 휴대폰 양식 검증 API",description = "휴대폰 번호를 받아 휴대폰 양식에 맞는지, 이미 등록된 휴대폰 번호인지 검증합니다. ")
    public BaseResponse<Boolean> checkedPhone(@Validated @RequestBody PostCheckPhoneReq postCheckPhoneReq) {
        return BaseResponse.onSuccess(Boolean.TRUE);
    }

    @ResponseBody
    @PostMapping("/sign-up/checked-username")
    @Operation(summary = "회원가입 시 유저아이디 검증 API",description = "유저아이디를 받아 유저 양식에 맞는지, 이미 등록된 유저아이디인지 검증합니다. ")
    public BaseResponse<Boolean> checkedUsername(@Validated @RequestBody PostCheckUsernameReq postCheckUsernameReq) {
        return BaseResponse.onSuccess(Boolean.TRUE);
    }

    @ResponseBody
    @PostMapping("/login")
    @Operation(summary = "로그인 API",description = "유저아이디와 비밀번호를 입력받아 일치하면 토큰을 반환합니다. ")
    public BaseResponse<PostLoginRes> login(@Validated @RequestBody PostLoginReq postLoginReq){
        return BaseResponse.onSuccess(authService.login(postLoginReq));
    }

    @PostMapping ("/mailSend")
    @Operation(summary = "휴대폰 인증 번호 발송 API",description = "휴대폰 번호를 받아 인증번호를 발송합니다.")
    public BaseResponse<String> mailSend(@Validated @RequestBody PostFindPhoneReq postFindPhoneReq){
        log.info("휴대폰 인증 번호 : {}", postFindPhoneReq.getPhoneNumber());
        return BaseResponse.onSuccess(authService.joinMail(postFindPhoneReq));
    }
    @PostMapping("/mailauthCheck")
    @Operation(summary = "휴대폰 인증 검증 API",description = "휴대폰 인증 번호를 받아 인증번호가 일치하는지 검증합니다.")
    public BaseResponse<Boolean> authCheck(@RequestBody @Valid PostFindCheckReq postFindCheckReq) {
        return BaseResponse.onSuccess(authService.checkAuthNum(postFindCheckReq));
    }

    @PatchMapping("/change-password")
    @Operation(summary = "비밀번호 변경 API",description = "검증 코드와 새로운 비밀번호를 입력받아 비밀번호를 변경합니다.")
    public BaseResponse<String> changePassword(@RequestBody @Valid PatchChangePasswordReq patchChangePasswordReq) {
        return BaseResponse.onSuccess(authService.changePassword(patchChangePasswordReq));
    }


    @PostMapping("/{socialLoginType}/login")
    @Operation(summary = "소셜 로그인 API",description = "소셜 로그인 코드를 받아 로그인합니다.")
    public BaseResponse<PostSocialRes> login(@PathVariable(name="socialLoginType") String socialLoginPath, @RequestParam("code") String code) {
        Constant.SocialLoginType socialLoginType = Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        return BaseResponse.onSuccess(authService.socialLogin(socialLoginType, code));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "리프레시토큰 재발급 API",description = "엑세스 토큰 만료 시 리프레시 토큰을 이용해 새로운 엑세스 토큰을 발급합니다.")
    public BaseResponse<PostRefreshRes> refreshToken(HttpServletRequest request, HttpServletResponse response)  {
        return BaseResponse.onSuccess(authService.refreshToken(request, response));
    }
}
