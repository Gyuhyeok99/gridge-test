package com.example.demo.src.admin.model;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.demo.src.admin.model.QGetCondUserRes is a Querydsl Projection type for GetCondUserRes
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetCondUserRes extends ConstructorExpression<GetCondUserRes> {

    private static final long serialVersionUID = 252089637L;

    public QGetCondUserRes(com.querydsl.core.types.Expression<? extends com.example.demo.src.user.entity.User> user) {
        super(GetCondUserRes.class, new Class<?>[]{com.example.demo.src.user.entity.User.class}, user);
    }

}

