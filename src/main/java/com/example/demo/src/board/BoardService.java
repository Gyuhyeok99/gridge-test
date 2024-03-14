package com.example.demo.src.board;


import com.example.demo.src.board.entity.Board;
import com.example.demo.src.board.entity.BoardImage;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    @Transactional
    public PostBoardRes createdBoard(User user, PostBoardReq postBoardReq) {
        Board board = BoardConverter.toBoard(postBoardReq, user);
        boardRepository.save(board);
        createdBoardImage(postBoardReq, board);
        return BoardConverter.toPostBoardRes(board.getId());
    }


    private void createdBoardImage(PostBoardReq postBoardReq, Board board) {
        if (postBoardReq.getBoardImageReqs() != null) {
            postBoardReq.getBoardImageReqs().forEach(imageReq -> {
                BoardImage boardImage = BoardConverter.toBoardImage(imageReq, board);
                boardImageRepository.save(boardImage);
            });
        }
    }
}
