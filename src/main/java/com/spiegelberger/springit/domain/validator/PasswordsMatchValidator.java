package com.spiegelberger.springit.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.spiegelberger.springit.domain.User;


public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch,User> {

	@Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getConfirmPassword());
    }

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {

    }
}
