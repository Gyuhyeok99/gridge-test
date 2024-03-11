package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.MustBeTrue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MustBeTrueValidator implements ConstraintValidator<MustBeTrue, Boolean> {

    @Override
    public void initialize(MustBeTrue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return Boolean.TRUE.equals(value);
    }
}