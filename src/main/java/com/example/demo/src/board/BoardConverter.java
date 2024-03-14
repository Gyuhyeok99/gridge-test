package com.example.demo.src.board;

import com.example.demo.src.board.entity.Board;
import com.example.demo.src.board.entity.BoardImage;
import com.example.demo.src.board.model.PostBoardImageReq;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardConverter {

    public static Board toBoard(PostBoardReq postBoardReq, User user){
        return Board.builder()
                .content(postBoardReq.getContent())
                .user(user)
                .build();
    }
    public static BoardImage toBoardImage(PostBoardImageReq postBoardImageReq, Board board){
        return BoardImage.builder()
                .imageUrl(postBoardImageReq.getImageUrl())
                .imageOrder(postBoardImageReq.getImageOrder())
                .board(board)
                .build();
    }

    public static PostBoardRes toPostBoardRes(Long boardId){
        return PostBoardRes.builder()
                .id(boardId)
                .build();
    }

}
