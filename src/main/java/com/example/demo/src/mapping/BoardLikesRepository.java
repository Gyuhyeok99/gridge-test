package com.example.demo.src.mapping;

import com.example.demo.src.mapping.entity.BoardLikes;
import com.example.demo.src.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {

    Optional<BoardLikes> findByBoardIdAndUser(Long boardId, User user);

    Long countByBoardId(Long boardId);
}
