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
    USERNAME_OK(HttpStatus.OK, "USER2000", "유저 아이디 양식 검증 성공"),
    PHONE_OK(HttpStatus.OK, "USER2001", "휴대폰 양식 검증 성공"),
    LOGIN_OK(HttpStatus.OK, "USER2002", "로그인 성공"),
    MAIL_SEND_OK(HttpStatus.OK, "USER2003", "휴대폰 인증번호 발송 성공"),
    PHONE_AUTH_OK(HttpStatus.OK, "USER2004", "휴대폰 인증 성공"),
    PASSWORD_CHANGE_OK(HttpStatus.OK, "USER2005", "비밀번호 변경 성공"),
    OAUTH_OK(HttpStatus.OK, "USER2006", "소셜 로그인 성공"),

    USER_SAVE_OK(HttpStatus.CREATED, "USER2010", "회원가입에 성공"),
    REFRESH_TOKEN_OK(HttpStatus.CREATED, "USER2011", "리프레쉬 토큰 재발급 성공"),

    //BoardController
    BOARD_OK(HttpStatus.OK, "BOARD2000", "게시글 단일 조회 성공"),
    BOARDS_OK(HttpStatus.OK, "BOARD2001", "전체 게시글 조회 성공"),
    BOARD_USER_OK(HttpStatus.OK, "BOARD2002", "게시글 수정/생성 시 필요한 회원아이디 조회 성공"),
    BOARD_LIKE_OK(HttpStatus.OK, "BOARD2003", "좋아요 토글 성공"),
    BOARD_EDIT_OK(HttpStatus.OK, "BOARD2004", "게시글 수정 성공"),
    BOARD_DELETE_OK(HttpStatus.OK, "BOARD2005", "게시글 삭제 성공"),

    BOARD_SAVE_OK(HttpStatus.CREATED, "BOARD2010", "게시글이 저장 성공"),
    BOARD_REPORT_OK(HttpStatus.CREATED, "BOARD2011", "게시글 신고 성공"),

    //CommentController
    COMMENT_OK(HttpStatus.OK, "COMMENT2000", "게시글 댓글 조회 성공"),
    COMMENT_EDIT_OK(HttpStatus.OK, "COMMENT2001", "댓글 수정 성공"),
    COMMENT_DELETE_OK(HttpStatus.OK, "COMMENT2002", "댓글 삭제 성공"),

    COMMENT_SAVE_OK(HttpStatus.CREATED, "COMMENT2010", "댓글 생성 성공"),

    //PaymentController
    PAYMENT_VALIDATE_OK(HttpStatus.OK, "PAYMENT2000", "결제 검증 성공"),
    PAYMENT_CANCEL_OK(HttpStatus.OK, "PAYMENT2001", "결제 취소 성공"),

    //UserController
    USER_NAME_OK(HttpStatus.OK, "USER2000", "유저 이름 조회 성공"),
    TERM_OK(HttpStatus.OK, "USER2001", "약관 재동의 성공"),
    USER_EDIT_OK(HttpStatus.OK, "USER2002", "이름 수정 성공"),
    USER_DELETE_OK(HttpStatus.OK, "USER2003", "유저 삭제 성공"),
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