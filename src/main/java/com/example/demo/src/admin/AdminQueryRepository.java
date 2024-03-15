package com.example.demo.src.admin;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.admin.model.*;
import com.querydsl.core.types.ConstantImpl;
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
import java.util.Collections;
import java.util.List;

import static com.example.demo.src.board.entity.QBoard.board;
import static com.example.demo.src.board.entity.QBoardImage.*;
import static com.example.demo.src.comment.entity.QComment.comment;
import static com.example.demo.src.mapping.entity.QBoardLikes.*;
import static com.example.demo.src.user.entity.QUser.user;
import static com.querydsl.jpa.JPAExpressions.*;

@Repository
@RequiredArgsConstructor
public class AdminQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<GetCondUserRes> searchUser(UserSearchCond condition, Pageable pageable) {
        List<GetCondUserRes> content = queryFactory
                .select(new QGetCondUserRes(user))
                .from(user)
                .where(nameLike(condition.getName()),
                        usernameLike(condition.getUsername()),
                        signupDateGoe(condition.getSignupDate()),
                        statusEq(condition.getState()))
                .orderBy(user.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(user.count())
                .from(user)
                .where(nameLike(condition.getName()),
                        usernameLike(condition.getUsername()),
                        signupDateGoe(condition.getSignupDate()),
                        statusEq(condition.getState()))
                .fetchCount();
        return new PageImpl<>(content, pageable, total);
    }

    public Page<GetCondBoardRes> searchBoard(BoardSearchCond boardSearchCond, Pageable pageable) {
        List<GetCondBoardRes> content = queryFactory
                .select(new QGetCondBoardRes(
                        board.id,
                        board.user.username,
                        board.content,
                        board.createdAt,
                        select(boardLikes.count())
                                .from(boardLikes)
                                .where(boardLikes.board.eq(board)),
                        select(comment.count())
                                .from(comment)
                                .where(comment.board.eq(board))
                ))
                .from(board)
                .leftJoin(board.user, user) // 게시글과 사용자 조인
                .where(
                        usernameLike(boardSearchCond.getUsername()),
                        signupDateGoe(boardSearchCond.getSignupDate()),
                        statusEq(boardSearchCond.getState())
                )
                .orderBy(board.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 전체 게시글 수 계산
        long total = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        usernameLike(boardSearchCond.getUsername()),
                        signupDateGoe(boardSearchCond.getSignupDate()),
                        statusEq(boardSearchCond.getState())
                )
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
    private BooleanExpression nameLike(String name) {
        return !(StringUtils.hasText(name)) ? null : user.name.contains(name);
    }

    private BooleanExpression usernameLike(String username) {
        return !(StringUtils.hasText(username)) ? null : user.username.contains(username);
    }

    private BooleanExpression signupDateGoe(String signupDate) {
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
