package com.mercadolibre.demo.controllers;

import com.mercadolibre.demo.dtos.requests.CreateLinkDTO;
import com.mercadolibre.demo.dtos.responses.ResponseIdLinkIdDTO;
import com.mercadolibre.demo.dtos.responses.ResponseInvalitLinkDTO;
import com.mercadolibre.demo.dtos.responses.ResponseStatisticDTO;
import com.mercadolibre.demo.exceptions.linkValidationException.IdNoMatchException;
import com.mercadolibre.demo.exceptions.linkValidationException.InvalidateLinkException;
import com.mercadolibre.demo.exceptions.linkValidationException.PasswordNoMatchException;
import com.mercadolibre.demo.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/link")
public class LinkedController {

    LinkService linkService;

    public LinkedController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseIdLinkIdDTO> createResourceLink(@Valid @RequestBody CreateLinkDTO createLinkDTO) throws IOException {
        return new ResponseEntity<>(linkService.createLink(createLinkDTO), HttpStatus.OK);
    }

    @GetMapping("/{linkID}")
    public ModelAndView redirectToLink(@PathVariable String linkID, @RequestParam(value = "password") String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException {
        return new ModelAndView("redirect:" + linkService.bringALink(linkID, password));
    }

    @GetMapping("/metrics/{linkId}")
        public ResponseEntity<ResponseStatisticDTO> metricLink(@PathVariable String linkId) throws IdNoMatchException {
        return new ResponseEntity<>(linkService.getStatisticsById(linkId),HttpStatus.OK);
        }

    @PostMapping("/invalit/{linkId}")
    public ResponseEntity<ResponseInvalitLinkDTO> invalitLink(@PathVariable String linkId, @RequestParam(value = "password") String password) throws PasswordNoMatchException, IdNoMatchException, InvalidateLinkException {
        return new ResponseEntity<>(linkService.invalidateLink(linkId,password),HttpStatus.OK);
    }
}
