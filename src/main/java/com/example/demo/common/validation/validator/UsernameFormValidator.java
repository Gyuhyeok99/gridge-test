package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.UsernameForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernameFormValidator implements ConstraintValidator<UsernameForm, String> {
    @Override
    public void initialize(UsernameForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && username.matches("^[a-z0-9._]+$");
    }
}