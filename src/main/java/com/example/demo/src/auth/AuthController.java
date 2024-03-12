package com.example.demo.src.auth;

import com.example.demo.common.Constant;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.auth.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Tag(name = "auth controller", description = "인증 인가 필요 없는 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입 API
     * [POST] /app/users
     * @return BaseResponse<PostUserRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/sign-up")
    @Operation(summary = "회원가입 API",description = "회원 가입 정보를 받아 회원 정보를 생성합니다.")
    public BaseResponse<PostUserRes> createUser(@Validated @RequestBody PostUserReq postUserReq) {
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

    /**
     * 로그인 API
     * [POST] /app/users/logIn
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> logIn(@RequestBody PostLoginReq postLoginReq){
        // TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
        // TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
        return BaseResponse.onSuccess(authService.logIn(postLoginReq));
    }

    /**
     * 유저 소셜 가입, 로그인 인증으로 리다이렉트 해주는 url
     * [GET] /app/users/auth/:socialLoginType/login
     * @return void
     */
    @GetMapping("/{socialLoginType}/login")
    public void socialLoginRedirect(@PathVariable(name="socialLoginType") String SocialLoginPath) throws IOException {
        Constant.SocialLoginType socialLoginType= Constant.SocialLoginType.valueOf(SocialLoginPath.toUpperCase());
        //oAuthService.accessRequest(socialLoginType);
    }


/*
    *//**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginPath (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code API Server 로부터 넘어오는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 java 객체 (access_token, jwt_token, user_num 등)
     *//*
    @ResponseBody
    @GetMapping(value = "/{socialLoginType}/login/callback")
    public BaseResponse<GetSocialOAuthRes> socialLoginCallback(
            @PathVariable(name = "socialLoginType") String socialLoginPath,
            @RequestParam(name = "code") String code) throws IOException, BaseException{
        log.info(">> 소셜 로그인 API 서버로부터 받은 code : {}", code);
        Constant.SocialLoginType socialLoginType = Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        //GetSocialOAuthRes getSocialOAuthRes = oAuthService.oAuthLoginOrJoin(socialLoginType,code);
        return BaseResponse.onSuccess(getSocialOAuthRes);
    }*/

}
