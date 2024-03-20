package com.example.demo.src.board.model;

import com.example.demo.common.validation.annotation.UsernameForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsernameRes {

    @UsernameForm
    private String username;
}
