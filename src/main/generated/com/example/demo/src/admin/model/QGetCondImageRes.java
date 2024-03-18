package com.example.demo.src.admin.model;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.demo.src.admin.model.QGetCondImageRes is a Querydsl Projection type for GetCondImageRes
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetCondImageRes extends ConstructorExpression<GetCondImageRes> {

    private static final long serialVersionUID = -1368226059L;

    public QGetCondImageRes(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<Integer> imageOrder) {
        super(GetCondImageRes.class, new Class<?>[]{long.class, String.class, int.class}, id, imageUrl, imageOrder);
    }

}

