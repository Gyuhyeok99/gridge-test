package com.example.demo.src.mapping.entity.enums;

public enum ReportContent {
    a("스팸"),
    b("나체 이미지 또는 성적 행위"),
    c("혐오 발언 또는 상징"),
    d("폭력 또는 위험한 단체"),
    e("불법 또는 규제 상품 판매"),
    f("따돌림 또는 괴롭힘"),
    g("지식 재산권 침해"),
    h("자살 또는 자해"),
    i("섭식 장애"),
    j("사기 또는 거짓"),
    k("거짓 정보"),
    l("마음에 들지 않습니다");

    private final String description;

    ReportContent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
