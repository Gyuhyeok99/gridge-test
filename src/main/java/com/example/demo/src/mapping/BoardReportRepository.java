package com.example.demo.src.mapping;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.mapping.entity.BoardReport;
import com.example.demo.src.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardReportRepository extends JpaRepository<BoardReport, Long> {

    Optional<BoardReport> findByBoardAndUserAndState(Board board, User user, State state);
    @Query(value = "SELECT br FROM BoardReport br " +
            "JOIN FETCH br.board b " +
            "JOIN FETCH br.user u " +
            "JOIN FETCH b.user bu " +
            "WHERE br.state = :state",
            countQuery = "SELECT count(br) FROM BoardReport br WHERE br.state = :state")
    Page<BoardReport> findByStateWithFetchJoin(@Param("state") State state, Pageable pageable);
}
