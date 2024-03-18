package com.example.demo.src.payment.entity.enums;

public enum PaymentStatus {
    OK("결제 완료"),
    FAIL("결제 실패"),
    CANCEL("결제 취소");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
