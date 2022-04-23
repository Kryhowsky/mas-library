package com.kryhowsky.maslibrary.validator.impl;

import com.kryhowsky.maslibrary.model.dto.PersonDto;
import com.kryhowsky.maslibrary.validator.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, PersonDto> {

    @Override
    public boolean isValid(PersonDto personDto, ConstraintValidatorContext constraintValidatorContext) {
        return personDto.getPassword().equals(personDto.getConfirmPassword());
    }

}
