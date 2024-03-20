package com.example.demo.src.auth;

import com.example.demo.common.Constant;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.auth.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.SuccessStatus.*;


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
    @ApiResponse(responseCode = "USER2010",description = "회원가입에 성공")
    @ApiResponse(responseCode = "USER4002", description = "중복된 아이디입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<PostUserRes> createUser(@Validated @RequestBody PostUserReq postUserReq) {
        log.info("Class: AuthController Method: createUser" + " is called by User: " + postUserReq.getUsername());
        return BaseResponse.of(USER_SAVE_OK, authService.createUser(postUserReq));
    }

    @ResponseBody
    @PostMapping("/sign-up/checked-username")
    @Operation(summary = "회원가입 시 유저아이디 검증 API",description = "유저아이디를 받아 유저 양식에 맞는지, 이미 등록된 유저아이디인지 검증합니다. ")
    @ApiResponse(responseCode = "USER2000",description = "유저 아이디 양식 검증 성공")
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<Boolean> checkedUsername(@Validated @RequestBody PostCheckUsernameReq postCheckUsernameReq) {
        return BaseResponse.of(USERNAME_OK, Boolean.TRUE);
    }

    @ResponseBody
    @PostMapping("/sign-up/checked-phoneNumber")
    @Operation(summary = "회원가입 시 휴대폰 양식 검증 API",description = "휴대폰 번호를 받아 휴대폰 양식에 맞는지, 이미 등록된 휴대폰 번호인지 검증합니다. ")
    @ApiResponse(responseCode = "USER2001",description = "휴대폰 양식 검증 성공")
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<Boolean> checkedPhone(@Validated @RequestBody PostCheckPhoneReq postCheckPhoneReq) {
        return BaseResponse.of(PHONE_OK, Boolean.TRUE);
    }


    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostLoginRes> login(@Validated @RequestBody PostLoginReq postLoginReq){
        return BaseResponse.of(LOGIN_OK, authService.login(postLoginReq));
    }

    @PostMapping ("/mailSend")
    @Operation(summary = "휴대폰 인증 번호 발송 API",description = "휴대폰 번호를 받아 인증번호를 발송합니다.")
    @ApiResponse(responseCode = "USER2003",description = "휴대폰 인증번호 발송 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> mailSend(@Validated @RequestBody PostFindPhoneReq postFindPhoneReq){
        log.info("휴대폰 인증 번호 : {}", postFindPhoneReq.getPhoneNumber());
        return BaseResponse.of(MAIL_SEND_OK, authService.joinMail(postFindPhoneReq));
    }
    @PostMapping("/mailauthCheck")
    @Operation(summary = "휴대폰 인증 검증 API",description = "휴대폰 인증 번호를 받아 인증번호가 일치하는지 검증합니다.")
    @ApiResponse(responseCode = "USER2004",description = "휴대폰 인증 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "USER4008", description = "핸드폰 인증에 실패했습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "USER4010", description = "정지당한 계정입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<Boolean> authCheck(@RequestBody @Valid PostFindCheckReq postFindCheckReq) {
        return BaseResponse.of(PHONE_AUTH_OK, authService.checkAuthNum(postFindCheckReq));
    }

    @PatchMapping("/change-password")
    @Operation(summary = "비밀번호 변경 API",description = "검증 코드와 새로운 비밀번호를 입력받아 비밀번호를 변경합니다.")
    @ApiResponse(responseCode = "USER2005",description = "비밀번호 변경 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "USER4008", description = "핸드폰 인증에 실패했습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "USER4009", description = "새비밀번호와 재입력한 새비밀번호가 일치하지 않습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "USER4010", description = "정지당한 계정입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> changePassword(@RequestBody @Valid PatchChangePasswordReq patchChangePasswordReq) {
        return BaseResponse.of(PASSWORD_CHANGE_OK, authService.changePassword(patchChangePasswordReq));
    }


    @PostMapping("/{socialLoginType}/login")
    @Operation(summary = "소셜 로그인 API",description = "소셜 로그인 코드를 받아 로그인합니다.")
    @ApiResponse(responseCode = "USER2006",description = "소셜 로그인 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "OAUTH4000", description = "알 수 없는 소셜 로그인 형식입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<PostSocialRes> login(@PathVariable(name="socialLoginType") String socialLoginPath, @RequestParam("code") String code) {
        Constant.SocialLoginType socialLoginType = Constant.SocialLoginType.valueOf(socialLoginPath.toUpperCase());
        return BaseResponse.of(OAUTH_OK, authService.socialLogin(socialLoginType, code));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "리프레시토큰 재발급 API",description = "엑세스 토큰 만료 시 리프레시 토큰을 이용해 새로운 엑세스 토큰을 발급합니다.")
    @ApiResponse(responseCode = "USER2011",description = "리프레쉬 토큰 재발급 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<PostRefreshRes> refreshToken(HttpServletRequest request, HttpServletResponse response)  {
        return BaseResponse.of(REFRESH_TOKEN_OK, authService.refreshToken(request, response));
    }

    @GetMapping("/health")
    @Operation(summary = "서버 상태 확인 API",description = "서버 상태를 확인합니다.")
    public BaseResponse<String> health() {
        return BaseResponse.onSuccess("I'm healthy");
    }
}
