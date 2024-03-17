package com.example.demo.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Before("within(@com.example.demo.common.log.Trace *)")
    public void logBefore(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = "anonymous"; // 기본값 설정

        if (authentication != null && authentication.isAuthenticated()) {
            currentUserName = authentication.getName();
        }

        // 클래스 이름을 가져옵니다.
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        // 클래스 이름을 로그에 포함시킵니다.
        log.info("Class: " + className + " Method: " + methodName + " is called by User: " + currentUserName);
    }
}
