package com.example.demo.src.board;


import com.example.demo.common.response.BaseResponse;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "board controller", description = "게시글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public BaseResponse<PostBoardRes> createBoard(@AuthenticationPrincipal User user, @Validated @RequestBody PostBoardReq postBoardReq) {
        return BaseResponse.onSuccess(boardService.createdBoard(user, postBoardReq));
    }
}
