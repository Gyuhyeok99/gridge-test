package com.example.demo.src.mapping.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.board.entity.Board;
import com.example.demo.src.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "board_likes")
public class BoardLikes extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "board_likes_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setBoard(Board board) {
        this.board = board;
        board.getBoardLikesList().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getBoardLikesList().add(this);
    }


}
