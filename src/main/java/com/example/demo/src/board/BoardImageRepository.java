package com.example.demo.src.board;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.board.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {

    List<BoardImage> findByBoardIdAndState(Long boardId, BaseEntity.State state);
}
