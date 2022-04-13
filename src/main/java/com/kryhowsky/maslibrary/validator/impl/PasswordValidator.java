package com.kryhowsky.maslibrary.validator.impl;

import com.kryhowsky.maslibrary.model.dto.UserDto;
import com.kryhowsky.maslibrary.validator.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }

}
