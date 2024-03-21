package com.example.demo.src.board.model;


import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchBoardRes {

    @NotNull
    private Long id;

}
