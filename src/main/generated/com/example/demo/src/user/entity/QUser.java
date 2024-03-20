package com.example.demo.src.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -282547529L;

    public static final QUser user = new QUser("user");

    public final com.example.demo.common.entity.QBaseEntity _super = new com.example.demo.common.entity.QBaseEntity(this);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOAuth = createBoolean("isOAuth");

    public final DateTimePath<java.time.LocalDateTime> lastLoginAt = createDateTime("lastLoginAt", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<com.example.demo.src.user.entity.enums.Role> role = createEnum("role", com.example.demo.src.user.entity.enums.Role.class);

    public final EnumPath<com.example.demo.common.Constant.SocialLoginType> socialLoginType = createEnum("socialLoginType", com.example.demo.common.Constant.SocialLoginType.class);

    //inherited
    public final EnumPath<com.example.demo.common.entity.BaseEntity.State> state = _super.state;

    public final BooleanPath subscriptionAgreed = createBoolean("subscriptionAgreed");

    public final DateTimePath<java.time.LocalDateTime> subscriptionAgreedAt = createDateTime("subscriptionAgreedAt", java.time.LocalDateTime.class);

    public final BooleanPath termsAgreed = createBoolean("termsAgreed");

    public final DatePath<java.time.LocalDate> termsAgreedDate = createDate("termsAgreedDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

