package com.example.demo.src.comment;


import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.comment.model.*;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.common.code.status.ErrorStatus.INVALID_PAGE;
import static com.example.demo.common.code.status.ErrorStatus.INVALID_SIZE_10;

@Slf4j
@Tag(name = "comment controller", description = "댓글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    @Operation(summary = "게시글 댓글 조회 API",description = "게시글 번호를 받아 해당 게시글의 댓글을 조회합니다.")
    public BaseResponse<Slice<GetCommentRes>> getComments(@PathVariable("boardId") Long boardId, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size != 10) {
            throw new BaseException(INVALID_SIZE_10);
        }
        return BaseResponse.onSuccess(commentService.getComments(boardId, page, size));
    }

    @PostMapping("/{boardId}")
    @Operation(summary = "댓글 생성 API",description = "게시글 번호와 댓글 내용을 받아 댓글을 생성합니다.")
    public BaseResponse<PostCommentRes> createComment(@AuthenticationPrincipal User user, @PathVariable("boardId") Long boardId, @Validated @RequestBody PostCommentReq postCommentReq) {
        return BaseResponse.onSuccess(commentService.createdComment(user, boardId, postCommentReq));
    }

    @PatchMapping("/{commentId}/edit")
    @Operation(summary = "댓글 수정 API",description = "댓글 번호와 수정할 댓글 내용을 받아 댓글을 수정합니다.")
    public BaseResponse<PatchCommentRes> editComment(@AuthenticationPrincipal User user, @PathVariable("commentId") Long commentId, @Validated @RequestBody PatchCommentReq patchCommentReq) {
        return BaseResponse.onSuccess(commentService.editComment(user, commentId, patchCommentReq));
    }

    @PatchMapping("/{commentId}/delete")
    @Operation(summary = "댓글 삭제 API",description = "댓글 번호를 받아 댓글을 삭제합니다.")
    public BaseResponse<String> deleteComment(@AuthenticationPrincipal User user, @PathVariable("commentId") Long commentId) {
        return BaseResponse.onSuccess(commentService.deleteComment(user, commentId));
    }


}
