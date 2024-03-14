package com.example.demo.src.board.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardImageReq {

    @NotNull
    private String imageUrl;

    @NotNull
    private Integer imageOrder;
}
