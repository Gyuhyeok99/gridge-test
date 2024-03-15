package com.example.demo.src.admin_user;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.model.GetCondUserRes;
import com.example.demo.src.model.QGetCondUserRes;
import com.example.demo.src.model.UserSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.demo.src.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class AdminUserQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<GetCondUserRes> searchUser(UserSearchCondition condition, Pageable pageable) {
        List<GetCondUserRes> content = queryFactory
                .select(new QGetCondUserRes(user))
                .from(user)
                .where(nameEq(condition.getName()),
                        usernameEq(condition.getUsername()),
                        signupDateEq(condition.getSignupDate()),
                        statusEq(condition.getState()))
                .orderBy(user.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(user.count())
                .from(user)
                .where(nameEq(condition.getName()),
                        usernameEq(condition.getUsername()),
                        signupDateEq(condition.getSignupDate()),
                        statusEq(condition.getState()))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression nameEq(String name) {
        return !(StringUtils.hasText(name)) ? null : user.name.contains(name);
    }

    private BooleanExpression usernameEq(String username) {
        return !(StringUtils.hasText(username)) ? null : user.username.contains(username);
    }

    private BooleanExpression signupDateEq(String signupDate) {
        if (!StringUtils.hasText(signupDate)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(signupDate, formatter);
        LocalDateTime startOfDay = localDate.atStartOfDay(); // 해당 날짜의 자정
        LocalDateTime endOfDay = localDate.plusDays(1).atStartOfDay(); // 다음 날의 자정
        return user.createdAt.goe(startOfDay).and(user.createdAt.lt(endOfDay));
    }

    private BooleanExpression statusEq(State status) {
        return user.state.eq(status);
    }


}
