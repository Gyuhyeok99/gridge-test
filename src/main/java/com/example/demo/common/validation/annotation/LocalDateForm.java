package com.example.demo.common.validation.annotation;

import com.example.demo.common.validation.validator.LocalDateFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = LocalDateFormValidator.class)

public @interface LocalDateForm {
    String message() default "날짜 형식이 유효하지 않습니다. yyyyMMdd 형식이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
