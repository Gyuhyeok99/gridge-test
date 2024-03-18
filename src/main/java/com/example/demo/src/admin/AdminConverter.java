package com.example.demo.src.admin;

import com.example.demo.common.log.entity.Log;
import com.example.demo.src.admin.model.GetLogRes;
import com.example.demo.src.admin.model.GetPaymentRes;
import com.example.demo.src.admin.model.GetReportRes;
import com.example.demo.src.admin.model.enums.DomainName;
import com.example.demo.src.mapping.entity.BoardReport;
import com.example.demo.src.payment.entity.Payment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminConverter {


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

    public static GetLogRes toGetLogRes(Log log) {
        return GetLogRes.builder()
                .id(log.getId())
                .methodName(log.getMethodName())
                .userName(log.getUserName())
                .createdAt(log.getCreatedAt())
                .build();
    }

    public static String toDomainControllerName(DomainName domainName) {
        String name = domainName.name().toLowerCase();
        return domainName.name().toLowerCase().substring(0, 1).toUpperCase() + name.substring(1) + "Controller";
    }

    public static GetPaymentRes toGetPaymentRes(Payment payment) {
        return GetPaymentRes.builder()
                .id(payment.getId())
                .detail(payment.getDetail())
                .paymentStatus(payment.getPaymentStatus())
                .paymentUid(payment.getPaymentUid())
                .username(payment.getUser().getUsername())
                .createdAt(payment.getCreatedAt())
                .build();
    }

}
