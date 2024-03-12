package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.PhoneUnique;
import com.example.demo.src.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PhoneUniqueValidator implements ConstraintValidator<PhoneUnique, String> {

    private final UserService userService;

    @Override
    public void initialize(PhoneUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return !userService.existsByPhoneNumber(phone);
    }
}