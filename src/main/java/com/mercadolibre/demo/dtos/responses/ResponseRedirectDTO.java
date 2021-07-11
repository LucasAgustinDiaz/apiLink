package com.mercadolibre.demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseRedirectDTO {

    private final Integer statusCode;
    private final String message;
    private final String urlToRedirect;
}
