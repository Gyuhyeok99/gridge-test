package com.example.demo.src.board;


import com.example.demo.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "board controller", description = "게시글 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

   /* @PostMapping
    public BaseResponse<Board> createBoard(@RequestBody BoardCreateRequest request) {
        // 게시글 생성 로직 구현
        // 생성된 게시글 정보 반환
    }*/
}
