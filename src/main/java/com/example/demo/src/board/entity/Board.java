package com.example.demo.src.board.entity;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.mapping.entity.BoardLikes;
import com.example.demo.src.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "board_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BoardImage> boardImageList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<BoardLikes> boardLikesList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();
    public void setUser(User user) {
        this.user = user;
        user.getBoardList().add(this);
    }


    public void setContent(String content) {
        this.content = content;
    }
}
