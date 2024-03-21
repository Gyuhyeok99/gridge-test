package com.example.demo.src.user;


import com.example.demo.common.log.Trace;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.user.entity.User;
import com.example.demo.src.user.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.SuccessStatus.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Trace
public class UserController {


    private final UserService userService;

    @GetMapping
    @Operation(summary = "유저 아이디 조회 API", description = "유저 아이디를 조회합니다.")
    @ApiResponse(responseCode = "USER2000",description = "유저 이름 조회 성공")
    public BaseResponse<String> getUser(@AuthenticationPrincipal User user) {
        return BaseResponse.of(USER_NAME_OK, user.getUsername());
    }

    @PatchMapping("/term")
    @Operation(summary = "약관 재동의 API", description = "1년 주기로 약관을 재동의합니다.")
    @ApiResponse(responseCode = "USER2001",description = "약관 재동의 성공")
    @ApiResponse(responseCode = "USER4012", description = "이미 약관에 동의하셨습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> patchTerm(@AuthenticationPrincipal User user) {
        userService.patchTerm(user);
        return BaseResponse.of(TERM_OK, "약관 재동의 완료");
    }

    @PatchMapping("/edit")
    @Operation(summary = "유저 이름 수정 API", description = "유저 이름을 수정합니다.")
    @ApiResponse(responseCode = "USER2002",description = "이름 수정 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> modifyUserName(@AuthenticationPrincipal User user, @RequestBody PatchUserReq patchUserReq){
        userService.modifyName(user.getId(), patchUserReq);
        return BaseResponse.of(USER_EDIT_OK, "정보 수정 완료");
    }


    @ResponseBody
    @PatchMapping("/delete")
    @Operation(summary = "유저 삭제 API", description = "유저 계정을 삭제합니다.")
    @ApiResponse(responseCode = "USER2003",description = "유저 삭제 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> deleteUser(@AuthenticationPrincipal User user){
        userService.deleteUser(user.getId());
        return BaseResponse.of(USER_DELETE_OK, "삭제 완료");
    }
}
