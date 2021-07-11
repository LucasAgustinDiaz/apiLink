package com.mercadolibre.demo.exceptions.parametersException;

import lombok.Getter;

@Getter
public class NoParametersMessage {
    private final Integer statusCode;
    private final String message;
    private final String parameterRequired;

    public NoParametersMessage(Integer statusCode, String message, String parameterRequired) {
        this.statusCode = statusCode;
        this.message = message;
        this.parameterRequired = parameterRequired;
    }
}
