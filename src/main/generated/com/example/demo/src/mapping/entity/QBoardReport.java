package com.example.demo.src.mapping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardReport is a Querydsl query type for BoardReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardReport extends EntityPathBase<BoardReport> {

    private static final long serialVersionUID = 2131254131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardReport boardReport = new QBoardReport("boardReport");

    public final com.example.demo.common.entity.QBaseEntity _super = new com.example.demo.common.entity.QBaseEntity(this);

    public final com.example.demo.src.board.entity.QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.example.demo.src.mapping.entity.enums.ReportContent> reportContent = createEnum("reportContent", com.example.demo.src.mapping.entity.enums.ReportContent.class);

    //inherited
    public final EnumPath<com.example.demo.common.entity.BaseEntity.State> state = _super.state;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.demo.src.user.entity.QUser user;

    public QBoardReport(String variable) {
        this(BoardReport.class, forVariable(variable), INITS);
    }

    public QBoardReport(Path<? extends BoardReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardReport(PathMetadata metadata, PathInits inits) {
        this(BoardReport.class, metadata, inits);
    }

    public QBoardReport(Class<? extends BoardReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.example.demo.src.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.example.demo.src.user.entity.QUser(forProperty("user")) : null;
    }

}

