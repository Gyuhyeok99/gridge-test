package com.example.demo.common.code.status;

import com.example.demo.common.code.BaseCode;
import com.example.demo.common.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    OK(HttpStatus.OK, "COMMON2000", "성공입니다."),

    //AdminController
    ADMIN_USER_SEARCH_OK(HttpStatus.OK, "ADMIN2000", "회원 검색 성공"),
    ADMIN_USER_OK(HttpStatus.OK, "ADMIN2001", "회원 단일 조회 성공"),
    ADMIN_USER_STOP_OK(HttpStatus.OK, "ADMIN2002", "회원 정지 성공"),
    ADMIN_BOARD_SEARCH_OK(HttpStatus.OK, "ADMIN2003", "게시글 검색 성공"),
    ADMIN_BOARD_OK(HttpStatus.OK, "ADMIN2004", "게시글 단일 조회 성공"),
    ADMIN_BOARD_DELETE_OK(HttpStatus.OK, "ADMIN2005", "게시글 삭제 성공"),
    ADMIN_REPORT_OK(HttpStatus.OK, "ADMIN2006", "신고 전체 조회 성공"),
    ADMIN_REPORT_DELETE_OK(HttpStatus.OK, "ADMIN2007", "신고 삭제 성공"),
    ADMIN_LOG_OK(HttpStatus.OK, "ADMIN2008", "로그 전체 조회 성공"),

    //AuthController
    USER_SAVE_OK(HttpStatus.CREATED, "USER2010", "회원가입에 성공"),
    REFRESH_TOKEN_OK(HttpStatus.CREATED, "USER2011", "리프레쉬 토큰 재발급 성공"),
    USERNAME_OK(HttpStatus.OK, "USER2000", "유저 아이디 양식 검증 성공"),
    PHONE_OK(HttpStatus.OK, "USER2001", "휴대폰 양식 검증 성공"),
    LOGIN_OK(HttpStatus.OK, "USER2002", "로그인 성공"),
    MAIL_SEND_OK(HttpStatus.OK, "USER2003", "휴대폰 인증번호 발송 성공"),
    PHONE_AUTH_OK(HttpStatus.OK, "USER2004", "휴대폰 인증 성공"),
    PASSWORD_CHANGE_OK(HttpStatus.OK, "USER2005", "비밀번호 변경 성공"),
    OAUTH_OK(HttpStatus.OK, "USER2006", "소셜 로그인 성공"),


    BOARD_OK(HttpStatus.OK, "BOARD200", "성공입니다."),
    BOARD_SAVE_OK(HttpStatus.CREATED, "BOARD201", "게시글이 저장되었습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}