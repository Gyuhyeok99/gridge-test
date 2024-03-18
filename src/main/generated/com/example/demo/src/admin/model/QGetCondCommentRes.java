package com.example.demo.src.admin.model;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.demo.src.admin.model.QGetCondCommentRes is a Querydsl Projection type for GetCondCommentRes
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetCondCommentRes extends ConstructorExpression<GetCondCommentRes> {

    private static final long serialVersionUID = 1749108593L;

    public QGetCondCommentRes(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<String> username) {
        super(GetCondCommentRes.class, new Class<?>[]{long.class, String.class, java.time.LocalDateTime.class, String.class}, id, content, createdAt, username);
    }

}

