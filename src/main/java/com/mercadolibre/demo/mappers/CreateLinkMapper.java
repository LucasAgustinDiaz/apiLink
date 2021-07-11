package com.mercadolibre.demo.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLinkMapper {
    private String url;
    private String password;
}
