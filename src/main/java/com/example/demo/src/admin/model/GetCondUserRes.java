package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.common.validation.annotation.UsernameForm;
import com.example.demo.src.user.entity.User;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class GetCondUserRes {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @UsernameForm
    private String username;
    @NotNull
    private String signupDate;
    private State state;

    @QueryProjection
    public GetCondUserRes(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.signupDate = user.getCreatedAt().toString();
        this.state = user.getState();
    }
}
