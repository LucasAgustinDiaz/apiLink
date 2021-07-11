package com.mercadolibre.demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data

public class ResponseInvalitLinkDTO {
    private String link;
    private String message;
    private Date date;

    public ResponseInvalitLinkDTO(String link, Date date, String linkId) {
        this.link = link;
        this.date = date;
        this.generateMessage(linkId);
    }

    public void generateMessage(String linkId) {
        this.message = "The link with id was correctly invalidated: "+ linkId;
    }
}
