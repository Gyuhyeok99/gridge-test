package com.example.demo.src.admin_user;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.model.GetCondUserRes;
import com.example.demo.src.model.UserSearchCondition;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.ErrorStatus.INVALID_PAGE;
import static com.example.demo.common.code.status.ErrorStatus.INVALID_SIZE;

@Slf4j
@Tag(name = "admin controller", description = "관리자 전용 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admins/users")
public class AdminUserController {

    private final AdminUserService adminUserService;
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 회원 검색 API", description = "관리자 전용 회원 검색 API입니다")
    public BaseResponse<Slice<GetCondUserRes>> getAdminUsers(
             @Validated @ModelAttribute UserSearchCondition userSearchCondition,
             @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size < 0) {
            throw new BaseException(INVALID_SIZE);
        }
        return BaseResponse.onSuccess(adminUserService.getAdminUsers(userSearchCondition, page, size));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 회원 Entity 조회 API", description = "관리자 전용 회원 Entity 조회 API입니다")
    public BaseResponse<User> getUser(@PathVariable("userId") Long userId) {
        return BaseResponse.onSuccess(adminUserService.getUser(userId));
    }

}
