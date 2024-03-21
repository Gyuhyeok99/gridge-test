package com.example.demo.src.admin.model;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class GetCondImageRes {

    @NotNull
    private Long id;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer imageOrder;

    @QueryProjection
    public GetCondImageRes(Long id, String imageUrl, Integer imageOrder) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageOrder = imageOrder;
    }
}
