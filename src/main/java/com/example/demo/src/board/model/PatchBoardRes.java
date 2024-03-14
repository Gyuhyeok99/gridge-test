package com.example.demo.src.board.model;


import com.example.demo.common.validation.annotation.MaxImageSize;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchBoardRes {

    private Long id;

}
