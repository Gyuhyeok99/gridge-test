package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.admin.model.BoardSearchCond;
import com.example.demo.src.admin.model.GetCondBoardRes;
import com.example.demo.src.admin.model.GetCondUserRes;
import com.example.demo.src.admin.model.UserSearchCond;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.example.demo.common.code.status.ErrorStatus.INVALID_PAGE;
import static com.example.demo.common.code.status.ErrorStatus.INVALID_SIZE;

@Slf4j
@Tag(name = "admin controller", description = "관리자 전용 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 회원 검색 API", description = "관리자 전용 회원 검색 API입니다")
    public BaseResponse<Page<GetCondUserRes>> getAdminUsers(
             @Validated @ModelAttribute UserSearchCond userSearchCond,
             @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size < 0) {
            throw new BaseException(INVALID_SIZE);
        }
        return BaseResponse.onSuccess(adminService.getAdminUsers(userSearchCond, page, size));
    }

    @GetMapping("/boards")
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 게시글 검색 API", description = "관리자 전용 게시글 검색 API입니다")
    public BaseResponse<Page<GetCondBoardRes>> getAdminBoards(
            @Validated @ModelAttribute BoardSearchCond boardSearchCond,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size < 0) {
            throw new BaseException(INVALID_SIZE);
        }
        return BaseResponse.onSuccess(adminService.getAdminBoards(boardSearchCond, page, size));
    }

    @GetMapping("users/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 단일 회원 조회 API", description = "관리자 전용 단일 회원 조회 API입니다")
    public BaseResponse<User> getUser(@PathVariable("userId") Long userId) {
        return BaseResponse.onSuccess(adminService.getUser(userId));
    }


    @PatchMapping("/users/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 회원 정지 API", description = "관리자 전용 회원 정지 API입니다")
    public BaseResponse<String> patchUser(@PathVariable("userId") Long userId, @RequestParam("state") State state) {
        return BaseResponse.onSuccess(adminService.patchUser(userId, state));
    }

}
