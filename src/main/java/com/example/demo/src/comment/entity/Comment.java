package com.example.demo.src.comment.entity;


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
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "comment_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2200)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    public void setUser(User user) {
        this.user = user;
        user.getCommentList().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getCommentList().add(this);
    }

    public void setState(State state) {
        this.state = state;
    }

}
