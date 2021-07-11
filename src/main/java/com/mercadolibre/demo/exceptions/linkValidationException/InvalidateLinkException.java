package com.mercadolibre.demo.exceptions.linkValidationException;

public class InvalidateLinkException extends Exception{
    private final String MESSAGE = "The id entered was: ";
    private final String ID_INVALID;
    private final String customMessage;
    public InvalidateLinkException(String message, String id) {
        super(message);
        this.ID_INVALID = id;
        this.customMessage = message;
    }

    public String generateMessage(){
        return this.customMessage +this.MESSAGE + this.ID_INVALID;
    }
}
