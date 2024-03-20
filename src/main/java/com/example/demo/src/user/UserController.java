package com.example.demo.src.user;


import com.example.demo.common.log.Trace;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.user.entity.User;
import com.example.demo.src.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Trace
public class UserController {


    private final UserService userService;

    @GetMapping
    public BaseResponse<String> getUser(@AuthenticationPrincipal User user) {
        return BaseResponse.onSuccess(user.getUsername());
    }

    //약관 재동의
    @PatchMapping("/term")
    public BaseResponse<String> patchTerm(@AuthenticationPrincipal User user) {
        userService.patchTerm(user);
        return BaseResponse.onSuccess("약관 재동의 완료");
    }

    /**
     * 유저정보변경 API
     * [PATCH] /app/users/:userId
     * @return BaseResponse<String>
     */
    @PatchMapping("/edit")
    public BaseResponse<String> modifyUserName(@AuthenticationPrincipal User user, @RequestBody PatchUserReq patchUserReq){
        userService.modifyUserName(user.getId(), patchUserReq);
        return BaseResponse.onSuccess("정보 수정 완료");
    }


    @ResponseBody
    @PatchMapping("/delete")
    public BaseResponse<String> deleteUser(@AuthenticationPrincipal User user){
        userService.deleteUser(user.getId());
        return BaseResponse.onSuccess("삭제 완료");
    }
}
