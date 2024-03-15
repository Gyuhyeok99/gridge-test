package com.example.demo.common.validation.annotation;

import com.example.demo.common.validation.validator.LocalDateTimeFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateTimeFormValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateTimeForm {
    String message() default "날짜 형식이 유효하지 않습니다. yyyyMMdd 형식이어야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
