package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.admin.model.*;
import com.example.demo.src.admin.model.enums.DomainName;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.Constant.validPage;
import static com.example.demo.common.code.status.SuccessStatus.*;

@Slf4j
@Tag(name = "admin controller", description = "관리자 전용 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 회원 검색 API", description = "관리자 전용 회원 검색 API 입니다")
    @ApiResponse(responseCode = "ADMIN2000",description = "회원 검색 성공")
    @ApiResponse(responseCode = "PAGE4000", description = "페이지는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAGE4002", description = "사이즈는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "page", description = "page 번호")
    @Parameter(name = "size", description = "size 번호")
    public BaseResponse<Page<GetCondUserRes>> getAdminUsers(
             @Validated @ModelAttribute UserSearchCond userSearchCond,
             @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        validPage(page, size);
        return BaseResponse.of(ADMIN_USER_SEARCH_OK, adminService.getAdminUsers(userSearchCond, page, size));
    }

    @GetMapping("users/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 단일 회원 조회 API", description = "관리자 전용 단일 회원 조회 API 입니다")
    @ApiResponse(responseCode = "ADMIN2001",description = "회원 단일 조회 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "userId", description = "userId 번호")
    public BaseResponse<User> getUser(@PathVariable("userId") Long userId) {
        return BaseResponse.of(ADMIN_USER_OK, adminService.getUser(userId));
    }

    @PatchMapping("/users/{userId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 회원 정지 API", description = "관리자 전용 회원 정지 API 입니다")
    @ApiResponse(responseCode = "ADMIN2002",description = "회원 정지 성공")
    @ApiResponse(responseCode = "USER4004", description = "일치하는 유저가 없습니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "userId", description = "userId 번호")
    @Parameter(name = "state", description = "유저 상태 ACTIVE(정상), INACTIVE(탈퇴), SUSPENDED(정지)")
    public BaseResponse<String> patchUser(@PathVariable("userId") Long userId, @RequestParam("state") State state) {
        return BaseResponse.of(ADMIN_USER_STOP_OK, adminService.patchUser(userId, state));
    }


    @GetMapping("/boards")
    @PreAuthorize("hasAuthority('admin:read')")
    @Operation(summary = "관리자 전용 게시글 검색 API", description = "관리자 전용 게시글 검색 API 입니다")
    @ApiResponse(responseCode = "ADMIN2003",description = "게시글 검색 성공")
    @ApiResponse(responseCode = "PAGE4000", description = "페이지는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAGE4002", description = "사이즈는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "page", description = "page 번호")
    @Parameter(name = "size", description = "size 번호")
    public BaseResponse<Page<GetCondBoardRes>> getAdminBoards(
            @Validated @ModelAttribute BoardSearchCond boardSearchCond,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        validPage(page, size);
        return BaseResponse.of(ADMIN_BOARD_SEARCH_OK, adminService.getAdminBoards(boardSearchCond, page, size));
    }

    @GetMapping("/boards/{boardId}")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    @Operation(summary = "관리자 전용 게시글 관련 단일 조회 API", description = "관리자 전용 게시글 관련 단일 조회 API 입니다")
    @ApiResponse(responseCode = "ADMIN2004",description = "게시글 단일 조회 성공")
    @ApiResponse(responseCode = "BOARD4000", description = "존재하지 않는 게시글입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "boardId", description = "boardId 번호")
    public BaseResponse<GetBoardRes> getBoard(@PathVariable("boardId") Long boardId) {
        return BaseResponse.of(ADMIN_BOARD_OK, adminService.getBoard(boardId));
    }

    @PatchMapping("/boards/{boardId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 게시글 삭제 API", description = "관리자 전용 게시글 삭제 API 입니다")
    @ApiResponse(responseCode = "ADMIN2005",description = "게시글 삭제 성공")
    @ApiResponse(responseCode = "BOARD4000", description = "존재하지 않는 게시글입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "boardId", description = "boardId 번호")
    public BaseResponse<String> patchBoard(@PathVariable("boardId") Long boardId) {
        return BaseResponse.of(ADMIN_BOARD_DELETE_OK, adminService.patchBoard(boardId));
    }

    @GetMapping("/reports")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    @Operation(summary = "관리자 전용 신고 전체 조회 API", description = "관리자 전용 신고 전체 조회 API 입니다")
    @ApiResponse(responseCode = "ADMIN2006",description = "신고 전체 조회 성공")
    @ApiResponse(responseCode = "PAGE4000", description = "페이지는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAGE4002", description = "사이즈는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "page", description = "page 번호")
    @Parameter(name = "size", description = "size 번호")
    public BaseResponse<Page<GetReportRes>> getReports(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        validPage(page, size);
        return BaseResponse.of(ADMIN_REPORT_OK, adminService.getReports(page, size));
    }

    @PatchMapping("/reports/{reportId}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Operation(summary = "관리자 전용 신고 삭제 API", description = "관리자 전용 신고 삭제 API 입니다")
    @ApiResponse(responseCode = "ADMIN2007",description = "신고 삭제 성공")
    @ApiResponse(responseCode = "BOARD4000", description = "존재하지 않는 게시글입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "reportId", description = "reportId 번호")
    public BaseResponse<String> patchReport(@PathVariable("reportId") Long reportId) {
        return BaseResponse.of(ADMIN_REPORT_DELETE_OK, adminService.patchReport(reportId));
    }

    @GetMapping("/logs")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    @Operation(summary = "관리자 전용 로그 전체 조회 API", description = "관리자 전용 로그 전체 조회 API 입니다")
    @ApiResponse(responseCode = "ADMIN2008",description = "로그 전체 조회 성공")
    @ApiResponse(responseCode = "PAGE4000", description = "페이지는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAGE4002", description = "사이즈는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "domainName", description = "domainName 이름(PAYMENT, USER, BOARD, COMMENT)")
    @Parameter(name = "page", description = "page 번호")
    @Parameter(name = "size", description = "size 번호")
    public BaseResponse<Page<?>> getLogs(@RequestParam("domainName") DomainName domainName, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        validPage(page, size);
        return BaseResponse.of(ADMIN_LOG_OK, adminService.getLogs(domainName, page, size));
    }

}
