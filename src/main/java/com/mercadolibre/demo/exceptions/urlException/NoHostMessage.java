package com.mercadolibre.demo.exceptions.urlException;

import lombok.Getter;

@Getter
public class NoHostMessage {

    private final Integer statusCode;
    private final String message;

    public NoHostMessage(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
