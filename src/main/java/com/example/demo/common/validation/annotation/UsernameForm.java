package com.example.demo.common.validation.annotation;

import com.example.demo.common.validation.validator.UsernameFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameFormValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameForm {
    String message() default "아이디는 소문자 영어, 숫자, '_', '.'만 포함할 수 있습니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}