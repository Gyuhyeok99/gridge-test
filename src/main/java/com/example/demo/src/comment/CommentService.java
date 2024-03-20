package com.example.demo.src.comment;

import com.example.demo.common.exceptions.BaseException;
import com.example.demo.src.board.BoardRepository;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.comment.model.*;
import com.example.demo.src.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.demo.common.Constant.CREATE_AT;
import static com.example.demo.common.code.status.ErrorStatus.NOT_FIND_COMMENT;
import static com.example.demo.common.entity.BaseEntity.State.ACTIVE;
import static com.example.demo.common.entity.BaseEntity.State.INACTIVE;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Slice<GetCommentRes> getComments(Long boardId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, CREATE_AT));
        return commentRepository.findByBoardIdAndState(boardId, ACTIVE, pageable).map(CommentConverter::toGetCommentRes);
    }
    @Transactional
    public PostCommentRes createdComment(User user, Long boardId, PostCommentReq postCommentReq) {
        Board board = boardRepository.findByIdAndState(boardId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_COMMENT));
        Comment comment = CommentConverter.toComment(user, board, postCommentReq);
        return CommentConverter.toPostCommentRes(commentRepository.save(comment).getId());
    }

    @Transactional
    public PatchCommentRes editComment(User user, Long commentId, PatchCommentReq patchCommentReq) {
        Comment comment = commentRepository.findByIdAndUserAndState(commentId, user, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_COMMENT));
        comment.editComment(patchCommentReq.getContent());
        return CommentConverter.toPatchCommentRes(comment);
    }

    @Transactional
    public String deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findByIdAndUserAndState(commentId, user, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_COMMENT));
        comment.setState(INACTIVE);
        return "댓글 삭제 성공";
    }


}
