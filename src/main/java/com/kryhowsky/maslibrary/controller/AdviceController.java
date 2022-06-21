package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.error.NotEnoughBookQuantityException;
import com.kryhowsky.maslibrary.error.TooManyActiveBorrowingsException;
import com.kryhowsky.maslibrary.model.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorDto handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        return new ErrorDto("Please provide correct login and password!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TooManyActiveBorrowingsException.class)
    public ErrorDto handleTooManyActiveBorrowingsException(TooManyActiveBorrowingsException tooManyActiveBorrowingsException) {
        return new ErrorDto(tooManyActiveBorrowingsException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughBookQuantityException.class)
    public ErrorDto handleNotEnoughBookQuantityException(NotEnoughBookQuantityException notEnoughBookQuantityException) {
        return new ErrorDto(notEnoughBookQuantityException.getMessage());
    }

}
