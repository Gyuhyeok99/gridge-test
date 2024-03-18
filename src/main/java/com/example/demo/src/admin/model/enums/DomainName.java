package com.example.demo.src.admin.model.enums;

public enum DomainName {

    USER("UserController"),
    BOARD("BoardController"),
    COMMENT("CommentController"),
    PAYMENT("PaymentController");
    private final String description;

    DomainName(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
