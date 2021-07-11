package com.mercadolibre.demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStatisticDTO {
    private String link_id;
    private String link;
    private Integer redirect;

}
