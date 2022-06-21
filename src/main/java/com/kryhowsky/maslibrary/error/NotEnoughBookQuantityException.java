package com.kryhowsky.maslibrary.error;

public class NotEnoughBookQuantityException extends RuntimeException {
    public NotEnoughBookQuantityException(String message) {
        super(message);
    }
}
