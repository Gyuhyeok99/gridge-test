package com.example.demo.src.board;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByIdAndUserAndState(Long id, User user, State state);

    Optional<Board> findByIdAndState(Long id, State state);


    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.state = :state and b.user.state = :state")
    Slice<Board> findByStateWithUser(@Param("state") State state, Pageable pageable);
}
