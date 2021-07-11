package com.mercadolibre.demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseIdLinkIdDTO {
    private final String link_id;
    private final String password;
}
