package com.mercadolibre.demo.interfaces;

import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import com.mercadolibre.demo.dtos.responses.ResponseInvalitLinkDTO;
import com.mercadolibre.demo.dtos.responses.ResponseStatisticDTO;
import com.mercadolibre.demo.exceptions.linkValidationException.IdNoMatchException;
import com.mercadolibre.demo.exceptions.linkValidationException.InvalidateLinkException;
import com.mercadolibre.demo.exceptions.linkValidationException.PasswordNoMatchException;

import java.io.IOException;

public interface ILinkService<T,P> {

    ResponseIdLinkIdDTO createLink(P p) throws IOException;

    void testLink(String link) throws IOException;

    String bringALink(String link_id, String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException;

    ResponseStatisticDTO getStatisticsById(String linkId) throws IdNoMatchException;

    ResponseInvalitLinkDTO invalidateLink(String linkId, String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException;
}
