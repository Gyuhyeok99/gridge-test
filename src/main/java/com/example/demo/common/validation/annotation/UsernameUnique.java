package com.example.demo.common.validation.annotation;


import com.example.demo.common.validation.validator.PhoneUniqueValidator;
import com.example.demo.common.validation.validator.UsernameUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {

    String message() default "이미 등록된 유저아이디입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
