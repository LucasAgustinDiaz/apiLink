package com.mercadolibre.demo.exceptions.urlException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

@ControllerAdvice(annotations = RestController.class)
public class UrlExceptionControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UnknownHostException.class)
    public ResponseEntity<NoHostMessage> handlerUnknownHostException(
            UnknownHostException e
    ) {
        return new ResponseEntity<>(new NoHostMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MalformedURLException.class)
    public ResponseEntity<NoHostMessage> handler(
            MalformedURLException e
    ) {
        return new ResponseEntity<>(new NoHostMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<NoHostMessage> handlerValidationUrlNo2ParamsException(
            IOException e
    ) {
        return new ResponseEntity<>(new NoHostMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ),HttpStatus.BAD_REQUEST) ;
    }


}
