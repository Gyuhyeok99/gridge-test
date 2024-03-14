package com.example.demo.src.comment;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.comment.model.PatchCommentReq;
import com.example.demo.src.comment.model.PatchCommentRes;
import com.example.demo.src.comment.model.PostCommentReq;
import com.example.demo.src.comment.model.PostCommentRes;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "comment controller", description = "댓글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

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
