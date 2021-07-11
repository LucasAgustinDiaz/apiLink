package com.mercadolibre.demo.exceptions.linkValidationException;

import lombok.Getter;

@Getter
public class LinkErrorMessage {
    private final Integer statusCode;
    private final String message;

    public LinkErrorMessage(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
