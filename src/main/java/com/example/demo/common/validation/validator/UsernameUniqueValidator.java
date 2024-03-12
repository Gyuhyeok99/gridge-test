package com.example.demo.common.validation.validator;

import com.example.demo.common.validation.annotation.UsernameUnique;
import com.example.demo.src.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    private final UserService userService;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.existByUsername(username);
    }
}