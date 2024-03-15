package com.example.demo.src.admin.model;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class GetCondImageRes {

    private Long id;
    private String imageUrl;
    private Integer imageOrder;

    @QueryProjection
    public GetCondImageRes(Long id, String imageUrl, Integer imageOrder) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageOrder = imageOrder;
    }
}
