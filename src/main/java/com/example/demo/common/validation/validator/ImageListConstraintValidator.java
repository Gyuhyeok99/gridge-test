package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.MaxImageSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ImageListConstraintValidator implements ConstraintValidator<MaxImageSize, List<?>> {

    @Override
    public void initialize(MaxImageSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.size() <= 10;
    }
}