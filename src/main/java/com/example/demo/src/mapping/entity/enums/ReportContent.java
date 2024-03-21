package com.example.demo.src.mapping.entity.enums;

public enum ReportContent {
    SPAM("스팸"),
    NUDE_OR_SEXUAL_ACTIVITY("나체 이미지 또는 성적 행위"),
    HATE_SPEECH_OR_SYMBOL("혐오 발언 또는 상징"),
    VIOLENCE_OR_DANGEROUS_ORGANIZATION("폭력 또는 위험한 단체"),
    ILLEGAL_OR_REGULATED_GOODS("불법 또는 규제 상품 판매"),
    BULLYING_OR_HARASSMENT("따돌림 또는 괴롭힘"),
    INTELLECTUAL_PROPERTY_INFRINGEMENT("지식 재산권 침해"),
    SUICIDE_OR_SELF_INJURY("자살 또는 자해"),
    EATING_DISORDER("섭식 장애"),
    FRAUD_OR_LIE("사기 또는 거짓"),
    MISINFORMATION("거짓 정보"),
    DISLIKE("마음에 들지 않습니다");

    private final String description;

    ReportContent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
