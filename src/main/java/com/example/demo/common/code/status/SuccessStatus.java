package com.example.demo.common.code.status;

import com.example.demo.common.code.BaseCode;
import com.example.demo.common.code.ReasonDTO;
import com.example.demo.src.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 관리자 페이지에서 사용하는 응답
    ADMIN_OK(HttpStatus.OK, "ADMIN200", "성공입니다."),

    // 사용자 페이지에서 사용하는 응답
    USER_OK(HttpStatus.OK, "USER200", "성공입니다."),
    USER_SAVE_OK(HttpStatus.CREATED, "USER201", "회원가입에 성공하였습니다."),

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