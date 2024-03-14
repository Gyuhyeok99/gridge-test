package com.example.demo.src.board.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardImageRes {

    private String imageUrl;
    private Integer imageOrder;

}
