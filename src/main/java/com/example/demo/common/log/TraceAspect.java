package com.example.demo.common.log;

import com.example.demo.common.log.entity.Log;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TraceAspect {

    private final LogRepository logRepository;

    @Before("within(@com.example.demo.common.log.Trace *)")
    public void logBefore(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = "anonymous"; // 기본값 설정

        if (authentication != null && authentication.isAuthenticated()) {
            currentUserName = authentication.getName();
        }

        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        logRepository.save(Log.builder()
                .userName(currentUserName)
                .className(className)
                .methodName(methodName)
                .build());
        log.info("Class: " + className + " Method: " + methodName + " is called by User: " + currentUserName);
    }
}
