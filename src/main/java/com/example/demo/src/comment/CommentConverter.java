package com.example.demo.src.comment;

import com.example.demo.src.board.entity.Board;
import com.example.demo.src.comment.model.GetCommentRes;
import com.example.demo.src.board.model.PostBoardRes;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.comment.model.PatchCommentRes;
import com.example.demo.src.comment.model.PostCommentReq;
import com.example.demo.src.comment.model.PostCommentRes;
import com.example.demo.src.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static GetCommentRes toGetCommentRes(Comment comment){
        return GetCommentRes.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .username(comment.getUser().getUsername())
                .build();
    }
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

    public static PatchCommentRes toPatchCommentRes(Comment comment) {
        return PatchCommentRes.builder()
                .id(comment.getId())
                .build();
    }
}
