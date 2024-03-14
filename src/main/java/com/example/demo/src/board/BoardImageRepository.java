package com.example.demo.src.board;

import com.example.demo.src.board.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
