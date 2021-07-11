package com.mercadolibre.demo.exceptions.linkValidationException;

public class IdNoMatchException extends Exception{
    private final String MESSAGE = "The element with that id was not found. The id entered was: ";
    private final String ID_INVALID;
    public IdNoMatchException(String message, String id) {
        super(message);
        this.ID_INVALID = id;
    }

    public String generateMessage(){
        return this.MESSAGE + this.ID_INVALID;
    }
}
