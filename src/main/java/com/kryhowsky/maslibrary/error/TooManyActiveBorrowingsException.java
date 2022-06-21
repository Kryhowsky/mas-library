package com.kryhowsky.maslibrary.error;

public class TooManyActiveBorrowingsException extends RuntimeException{
    public TooManyActiveBorrowingsException(String message) {
        super(message);
    }
}
