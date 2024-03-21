package com.example.demo.src.board.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardImageRes {

    @NotNull
    private String imageUrl;
    @NotNull
    private Integer imageOrder;

}
