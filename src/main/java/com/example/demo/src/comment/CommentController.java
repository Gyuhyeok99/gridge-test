package com.example.demo.src.comment;


import com.example.demo.common.log.Trace;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.comment.model.*;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.SuccessStatus.*;

@Slf4j
@Tag(name = "comment controller", description = "댓글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
@Trace
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    @Operation(summary = "게시글 댓글 조회 API",description = "게시글 번호를 받아 해당 게시글의 댓글을 조회합니다.")
    @ApiResponse(responseCode = "COMMENT2000",description = "게시글 댓글 조회 성공")
    @ApiResponse(responseCode = "PAGE4000", description = "페이지는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "PAGE4002", description = "사이즈는 0 이상이어야 합니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "boardId", description = "boardId 번호")
    @Parameter(name = "page", description = "page 번호")
    @Parameter(name = "size", description = "size 번호")
    public BaseResponse<Slice<GetCommentRes>> getComments(@PathVariable("boardId") Long boardId, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return BaseResponse.of(COMMENT_OK, commentService.getComments(boardId, page, size));
    }

    @PostMapping("/{boardId}")
    @Operation(summary = "댓글 생성 API",description = "게시글 번호와 댓글 내용을 받아 댓글을 생성합니다.")
    @ApiResponse(responseCode = "COMMENT2010",description = "댓글 생성 성공")
    @ApiResponse(responseCode = "COMMENT4000", description = "존재하지 않는 코멘트입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @ApiResponse(responseCode = "COMMON4000", description = "잘못된 요청입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "boardId", description = "boardId 번호")
    public BaseResponse<PostCommentRes> createComment(@AuthenticationPrincipal User user, @PathVariable("boardId") Long boardId, @Validated @RequestBody PostCommentReq postCommentReq) {
        return BaseResponse.of(COMMENT_SAVE_OK, commentService.createdComment(user, boardId, postCommentReq));
    }

    @PatchMapping("/{commentId}/edit")
    @Operation(summary = "댓글 수정 API",description = "댓글 번호와 수정할 댓글 내용을 받아 댓글을 수정합니다.")
    @ApiResponse(responseCode = "COMMENT2001",description = "댓글 수정 성공")
    @ApiResponse(responseCode = "COMMENT4000", description = "존재하지 않는 코멘트입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @Parameter(name = "commentId", description = "commentId 번호")
    public BaseResponse<PatchCommentRes> editComment(@AuthenticationPrincipal User user, @PathVariable("commentId") Long commentId, @Validated @RequestBody PatchCommentReq patchCommentReq) {
        return BaseResponse.of(COMMENT_EDIT_OK, commentService.editComment(user, commentId, patchCommentReq));
    }

    @PatchMapping("/{commentId}/delete")
    @Operation(summary = "댓글 삭제 API",description = "댓글 번호를 받아 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "COMMENT2002",description = "댓글 삭제 성공")
    @ApiResponse(responseCode = "COMMENT4000", description = "존재하지 않는 코멘트입니다.",content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public BaseResponse<String> deleteComment(@AuthenticationPrincipal User user, @PathVariable("commentId") Long commentId) {
        return BaseResponse.of(COMMENT_DELETE_OK, commentService.deleteComment(user, commentId));
    }
}
