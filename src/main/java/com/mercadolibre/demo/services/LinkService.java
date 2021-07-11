package com.mercadolibre.demo.services;

import com.mercadolibre.demo.dtos.responses.ResponseInvalitLinkDTO;
import com.mercadolibre.demo.dtos.responses.ResponseStatisticDTO;
import com.mercadolibre.demo.exceptions.linkValidationException.IdNoMatchException;
import com.mercadolibre.demo.exceptions.linkValidationException.InvalidateLinkException;
import com.mercadolibre.demo.exceptions.linkValidationException.PasswordNoMatchException;
import com.mercadolibre.demo.mappers.LinkMapper;
import com.mercadolibre.demo.dtos.requests.CreateLinkDTO;
import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import com.mercadolibre.demo.interfaces.ILinkService;
import com.mercadolibre.demo.mappers.CreateLinkMapper;
import com.mercadolibre.demo.models.MessageCaller;
import com.mercadolibre.demo.repositories.LinkRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Date;

@Service
public class LinkService implements ILinkService<LinkMapper, CreateLinkDTO> {

    // services
    GeneratorLinkIDService generatorLinkIDService;

    // repositories
    LinkRepository linkRepository;

    // local variables
    URLValidator urlValidator;

    public LinkService(LinkRepository linkRepository, GeneratorLinkIDService generatorLinkIDService) {
        this.linkRepository = linkRepository;
        this.generatorLinkIDService = generatorLinkIDService;
        urlValidator = new URLValidator();
    }

    @Override
    public ResponseIdLinkIdDTO createLink(CreateLinkDTO createLinkDTO) throws IOException {
        testLink(createLinkDTO.getUrl()); // validate url
        return linkRepository.addToRepository(new CreateLinkMapper(createLinkDTO.getUrl(), createLinkDTO.getPassword()));
    }

    @Override
    public void testLink(String link) throws IOException{
        try {
            URL testUrl = new URL(link);
            URLConnection conn = testUrl.openConnection();
            conn.connect();
        } catch (MalformedURLException e){
            throw new MalformedURLException(MessageCaller.URL_NO_VALIDATE);
            //
        } catch (UnknownHostException e) {
            throw new UnknownHostException(MessageCaller.UNKONOWN_HOST);
        }
        catch (IOException e) {
            throw new IOException(MessageCaller.CONNECTION_NOT_ESTABLISHED);
        }
    }

    @Override
    public String bringALink(String link_id, String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException {
        return linkRepository.getLinkFromLinkId(link_id,password);
    }

    @Override
    public ResponseStatisticDTO getStatisticsById(String linkId) throws IdNoMatchException {
        LinkMapper linkMapper = linkRepository.getLinkStatistics(linkId);
        return new ResponseStatisticDTO(linkMapper.getLink_id(),linkMapper.getLink(), linkMapper.getRedirects());
    }

    @Override
    public ResponseInvalitLinkDTO invalidateLink(String linkId, String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException {
        LinkMapper linkMapper = linkRepository.generateInvalidation(linkId, password);
        return new ResponseInvalitLinkDTO(linkMapper.getLink_id(), new Date(),linkId);
    }


}
