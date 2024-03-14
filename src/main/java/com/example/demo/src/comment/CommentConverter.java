package com.example.demo.src.comment;

import com.example.demo.src.board.entity.Board;
import com.example.demo.src.board.model.PostBoardReq;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.comment.model.PostCommentReq;
import com.example.demo.src.comment.model.PostCommentRes;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(User user, Board board, PostCommentReq postCommentReq){
        return Comment.builder()
                .content(postCommentReq.getContent())
                .user(user)
                .board(board)
                .build();
    }
    public static PostCommentRes toPostCommentRes(Long commentId){
        return PostCommentRes.builder()
                .id(commentId)
                .build();
    }

    public static PostBoardRes toPostBoardRes(Long boardId){
        return PostBoardRes.builder()
                .id(boardId)
                .build();
    }
}
