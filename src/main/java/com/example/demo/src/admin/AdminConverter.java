package com.example.demo.src.admin;

import com.example.demo.src.admin.model.GetCondCommentRes;
import com.example.demo.src.admin.model.GetCondImageRes;
import com.example.demo.src.admin.model.GetReportRes;
import com.example.demo.src.board.entity.BoardImage;
import com.example.demo.src.comment.entity.Comment;
import com.example.demo.src.mapping.entity.BoardReport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminConverter {

    public static GetCondImageRes toGetCondImageRes(BoardImage boardImage) {
        return GetCondImageRes.builder()
                .id(boardImage.getId())
                .imageUrl(boardImage.getImageUrl())
                .imageOrder(boardImage.getImageOrder())
                .build();
    }

    public static GetCondCommentRes toGetCondCommentRes(Comment comment) {
        return GetCondCommentRes.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .username(comment.getUser().getUsername())
                .build();
    }

    public static GetReportRes toGetReportRes(BoardReport boardReport) {
        return GetReportRes.builder()
                .id(boardReport.getId())
                .boardId(boardReport.getBoard().getId())
                .reportContent(boardReport.getReportContent())
                .reporter(boardReport.getUser().getUsername())
                .username(boardReport.getBoard().getUser().getUsername())
                .createAt(boardReport.getCreatedAt())
                .build();
    }


}
