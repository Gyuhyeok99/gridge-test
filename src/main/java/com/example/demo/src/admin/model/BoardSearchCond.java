package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.common.validation.annotation.LocalDateTimeForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardSearchCond {

    private String username;
    @LocalDateTimeForm
    private String signupDate;
    private BaseEntity.State state;
}
