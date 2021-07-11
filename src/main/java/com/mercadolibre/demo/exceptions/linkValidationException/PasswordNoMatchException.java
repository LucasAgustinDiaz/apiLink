package com.mercadolibre.demo.exceptions.linkValidationException;

public class PasswordNoMatchException extends Exception{
    private final String MESSAGE;
    public PasswordNoMatchException(String message) {
        super(message);
        this.MESSAGE = message;
    }
}
