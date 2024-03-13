package com.example.demo.src.board.entity;


import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.board.entity.Board;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "board_image")
public class BoardImage extends BaseEntity {


    @Id // PK를 의미하는 어노테이션
    @Column(name = "board_image_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void setBoard(Board board) {
        this.board = board;
        board.getBoardImageList().add(this);
    }

}
