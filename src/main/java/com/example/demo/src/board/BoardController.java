package com.example.demo.src.board;


import com.example.demo.common.exceptions.BaseException;
import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.board.model.*;
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
@Tag(name = "board controller", description = "게시글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardId}")
    @Operation(summary = "단일 게시글 조회 API", description = "게시글 번호를 받아 게시글을 조회합니다")
    public BaseResponse<GetBoardRes> getBoard (@PathVariable("boardId") Long boardId) {
        return BaseResponse.onSuccess(boardService.getBoard(boardId));
    }

    @GetMapping
    @Operation(summary = "전체 게시글 조회 API", description = "전체 게시글을 페이징하여 조회합니다 page는 0부터 시작합니다. size는 10으로 요청해야합니다.")
    public BaseResponse<Slice<GetBoardRes>> getBoards(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        if (page < 0) {
            throw new BaseException(INVALID_PAGE);
        }
        if (size != 10) {
            throw new BaseException(INVALID_SIZE_10);
        }
        return BaseResponse.onSuccess(boardService.getBoards(page, size));
    }

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
    @Operation(summary = "게시글 좋아요 API",description = "게시글 번호를 받아 게시글을 좋아요합니다. 이미 좋아요한 경우 취소됩니다.")
    public BaseResponse<PostLikesRes> toggleLike(@PathVariable("boardId") Long boardId, @AuthenticationPrincipal User user) {
        return BaseResponse.onSuccess(( boardService.toggleLike(boardId, user)));
    }

    @PostMapping("/{boardId}/report")
    @Operation(summary = "게시글 신고 API",description = "게시글 번호를 받아 게시글을 신고합니다." +
            "   \n\n \n\n" +
            "    SPAM(\"스팸\"),\n" +
            "    NUDE_OR_SEXUAL_ACTIVITY(\"나체 이미지 또는 성적 행위\"),\n" +
            "    HATE_SPEECH_OR_SYMBOL(\"혐오 발언 또는 상징\"),\n" +
            "    VIOLENCE_OR_DANGEROUS_ORGANIZATION(\"폭력 또는 위험한 단체\"),\n" +
            "    ILLEGAL_OR_REGULATED_GOODS(\"불법 또는 규제 상품 판매\"),\n" +
            "    BULLYING_OR_HARASSMENT(\"따돌림 또는 괴롭힘\"),\n" +
            "    INTELLECTUAL_PROPERTY_INFRINGEMENT(\"지식 재산권 침해\"),\n" +
            "    SUICIDE_OR_SELF_INJURY(\"자살 또는 자해\"),\n" +
            "    EATING_DISORDER(\"섭식 장애\"),\n" +
            "    FRAUD_OR_LIE(\"사기 또는 거짓\"),\n" +
            "    MISINFORMATION(\"거짓 정보\"),\n" +
            "    DISLIKE(\"마음에 들지 않습니다\")\n")
    public BaseResponse<PostReportRes> reportBoard(@PathVariable("boardId") Long boardId, @AuthenticationPrincipal User user, @Validated @RequestBody PostReportReq postReportReq) {
        return BaseResponse.onSuccess(boardService.reportBoard(boardId, user, postReportReq));
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
