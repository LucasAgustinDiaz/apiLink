package com.mercadolibre.demo.exceptions.linkValidationException;

import com.mercadolibre.demo.exceptions.parametersException.NoParametersMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class LinkValidationExceptionControllerAdvice {


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordNoMatchException.class)
    public ResponseEntity<LinkErrorMessage> handlerPasswordNoMatchException(
            PasswordNoMatchException e
    ) {
        return new ResponseEntity<>(new LinkErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNoMatchException.class)
    public ResponseEntity<LinkErrorMessage> handlerIdNoMatchException(
            IdNoMatchException e
    ) {
        return new ResponseEntity<>(new LinkErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.generateMessage()
        ),HttpStatus.NOT_FOUND) ;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidateLinkException.class)
    public ResponseEntity<LinkErrorMessage> handlerIdNoMatchException(
            InvalidateLinkException e
    ) {
        return new ResponseEntity<>(new LinkErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.generateMessage()
        ),HttpStatus.NOT_FOUND) ;
    }

}
