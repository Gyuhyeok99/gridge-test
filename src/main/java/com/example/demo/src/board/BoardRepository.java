package com.example.demo.src.board;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndUserAndState(Long id, User user, State state);
}
