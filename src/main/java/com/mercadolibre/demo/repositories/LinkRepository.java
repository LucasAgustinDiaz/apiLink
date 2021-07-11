package com.mercadolibre.demo.repositories;


import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import com.mercadolibre.demo.exceptions.linkValidationException.IdNoMatchException;
import com.mercadolibre.demo.exceptions.linkValidationException.InvalidateLinkException;
import com.mercadolibre.demo.exceptions.linkValidationException.PasswordNoMatchException;
import com.mercadolibre.demo.mappers.CreateLinkMapper;
import com.mercadolibre.demo.mappers.LinkMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

/**
 * Data Controller
 */
@Repository
public class LinkRepository {

    JsonRepository jsonRepository;

    public LinkRepository(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public ResponseIdLinkIdDTO addToRepository(CreateLinkMapper createLinkMapper) throws IOException {
        return jsonRepository.addResourceToDataBase(createLinkMapper);
    }

    public String getLinkFromLinkId(String linkId, String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException {
        Optional<LinkMapper> linkMapper = jsonRepository.getOneElementById(linkId);
        if (linkMapper.isEmpty())
            throw new IdNoMatchException("There is no element with that Id", linkId);

        if (!linkMapper.get().passwordMatch(password))
            throw new PasswordNoMatchException("The password entered does not exist in our records");

        if (!linkMapper.get().getActive())
            throw new InvalidateLinkException("he element with that id is inactive. ", linkMapper.get().getLink_id());

        jsonRepository.incrementCounter(linkMapper.get());
        return linkMapper.get().getLink();
    }

    public LinkMapper getLinkStatistics(String linkId) throws IdNoMatchException {
        Optional<LinkMapper> linkMapper = jsonRepository.getOneElementById(linkId);
        if (linkMapper.isEmpty())
            throw new IdNoMatchException("There is no element with that Id", linkId);

        return linkMapper.get();
    }

    public LinkMapper generateInvalidation(String linkId, String password) throws IdNoMatchException, PasswordNoMatchException, InvalidateLinkException {
        Optional<LinkMapper> linkMapper = jsonRepository.getOneElementById(linkId);

        if (linkMapper.isEmpty())
            throw new IdNoMatchException("There is no element with that Id", linkId);

        if (!linkMapper.get().passwordMatch(password))
            throw new PasswordNoMatchException("The password entered does not exist in our records");

        if (!linkMapper.get().getActive())
            throw new InvalidateLinkException("This link is already invalid. ", linkId);

        jsonRepository.invalidate(linkMapper.get());
        return linkMapper.get();
    }
}













