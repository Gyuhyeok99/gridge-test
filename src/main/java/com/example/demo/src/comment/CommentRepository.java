package com.example.demo.src.comment;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndUserAndState(Long id, User user, State state);
    List<Comment> findByBoardIdAndState(Long id, State state);
    Slice<Comment> findByBoardIdAndState(Long id, State state, Pageable pageable);

    Long countByBoardId(Long boardId);
}
