package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        // 간단한 전화번호 검증 로직 예시 (실제 로직은 요구사항에 맞게 구현해야 함)
        return phoneNumber != null && phoneNumber.matches("^\\+\\d{1,3}-\\d{2,3}-\\d{3,4}-\\d{4}$");
    }
}