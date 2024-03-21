package com.example.demo.common.log.entity;

import com.example.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "log")
public class Log extends BaseEntity {

    @Id // PK를 의미하는 어노테이션
    @Column(name = "log_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String className;
    @Column(nullable = false)
    private String methodName;

    public Log(String userName, String className, String methodName) {
        this.userName = userName;
        this.className = className;
        this.methodName = methodName;
    }
}
