package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.PhoneForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class PhoneFormValidator implements ConstraintValidator<PhoneForm, String> {
    @Override
    public void initialize(PhoneForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return phoneNumber != null && phoneNumber.matches("^\\+\\d{1,3}-\\d{2,3}-\\d{3,4}-\\d{4}$");
    }

}