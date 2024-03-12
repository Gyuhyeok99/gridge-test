package com.example.demo.common.validation.annotation;

import com.example.demo.common.validation.validator.PhoneFormValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PhoneFormValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneForm {
    String message() default "핸드폰 번호 양식에 맞지 않습니다. 예시: +82-10-0000-0000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}