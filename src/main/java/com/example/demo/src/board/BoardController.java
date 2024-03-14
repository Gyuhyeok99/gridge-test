package com.example.demo.src.board;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.board.model.*;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(name = "board controller", description = "게시글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/create")
    @Operation(summary = "게시글 생성 시 필요한 회원아이디 조회 API",description = "게시글 생성 시 필요한 회원아이디를 조회합니다")
    public BaseResponse<GetUsernameRes> getUsername1(@AuthenticationPrincipal User user) {
        return BaseResponse.onSuccess(new GetUsernameRes(user.getUsername()));
    }
    @GetMapping("/edit")
    @Operation(summary = "게시글 수정 시 필요한 회원아이디 조회 API",description = "게시글 수정 시 필요한 회원아이디를 조회합니다")
    public BaseResponse<GetUsernameRes> getUsername2(@AuthenticationPrincipal User user) {
        return BaseResponse.onSuccess(new GetUsernameRes(user.getUsername()));
    }
    @PostMapping
    @Operation(summary = "게시글 생성 API",description = "게시글 정보를 받아 게시글을 생성합니다.")
    public BaseResponse<PostBoardRes> createBoard(@AuthenticationPrincipal User user, @Validated @RequestBody PostBoardReq postBoardReq) {
        return BaseResponse.onSuccess(boardService.createdBoard(user, postBoardReq));
    }

    @PostMapping("/{boardId}/likes")
    public BaseResponse<PostLikesRes> toggleLike(@PathVariable("boardId") Long boardId, @AuthenticationPrincipal User user) {
        return BaseResponse.onSuccess(( boardService.toggleLike(boardId, user)));
    }

    @PatchMapping("/{boardId}/edit")
    @Operation(summary = "게시글 수정 API",description = "게시글 정보를 받아 게시글을 수정합니다.")
    public BaseResponse<PatchBoardRes> editedBoard(@AuthenticationPrincipal User user, @PathVariable("boardId") Long boardId, @Validated @RequestBody PatchBoardReq patchBoardReq) {
        return BaseResponse.onSuccess(boardService.editedBoard(user, boardId, patchBoardReq));
    }

    @PatchMapping("/{boardId}/delete")
    @Operation(summary = "게시글 삭제 API",description = "게시글 번호를 받아 게시글을 삭제합니다.")
    public BaseResponse<String> deletedBoard(@AuthenticationPrincipal User user, @PathVariable("boardId") Long boardId) {
        return BaseResponse.onSuccess(boardService.deletedBoard(user, boardId));
    }

}
