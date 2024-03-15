package com.example.demo.src.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1593225031L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.example.demo.common.entity.QBaseEntity _super = new com.example.demo.common.entity.QBaseEntity(this);

    public final ListPath<BoardImage, QBoardImage> boardImageList = this.<BoardImage, QBoardImage>createList("boardImageList", BoardImage.class, QBoardImage.class, PathInits.DIRECT2);

    public final ListPath<com.example.demo.src.mapping.entity.BoardLikes, com.example.demo.src.mapping.entity.QBoardLikes> boardLikesList = this.<com.example.demo.src.mapping.entity.BoardLikes, com.example.demo.src.mapping.entity.QBoardLikes>createList("boardLikesList", com.example.demo.src.mapping.entity.BoardLikes.class, com.example.demo.src.mapping.entity.QBoardLikes.class, PathInits.DIRECT2);

    public final ListPath<com.example.demo.src.comment.entity.Comment, com.example.demo.src.comment.entity.QComment> commentList = this.<com.example.demo.src.comment.entity.Comment, com.example.demo.src.comment.entity.QComment>createList("commentList", com.example.demo.src.comment.entity.Comment.class, com.example.demo.src.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final EnumPath<com.example.demo.common.entity.BaseEntity.State> state = _super.state;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.demo.src.user.entity.QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.demo.src.user.entity.QUser(forProperty("user")) : null;
    }

}

