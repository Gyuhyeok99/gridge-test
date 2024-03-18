package com.example.demo.src.admin.model;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.demo.src.admin.model.QGetCondBoardRes is a Querydsl Projection type for GetCondBoardRes
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetCondBoardRes extends ConstructorExpression<GetCondBoardRes> {

    private static final long serialVersionUID = 1102139786L;

    public QGetCondBoardRes(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<Long> likesCount, com.querydsl.core.types.Expression<Long> commentsCount) {
        super(GetCondBoardRes.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, long.class, long.class}, id, username, content, createdAt, likesCount, commentsCount);
    }

}

