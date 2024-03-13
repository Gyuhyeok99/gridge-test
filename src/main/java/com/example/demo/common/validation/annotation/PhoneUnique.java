package com.example.demo.common.validation.annotation;

import com.example.demo.common.validation.validator.PhoneUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneUniqueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneUnique {
    String message() default "이미 등록된 휴대폰 번호입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
