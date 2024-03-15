package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class GetCondUserRes {

    private String name;
    private String username;
    private String signupDate;
    private State state;

    @QueryProjection
    public GetCondUserRes(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.signupDate = user.getCreatedAt().toString();
        this.state = user.getState();
    }
}
