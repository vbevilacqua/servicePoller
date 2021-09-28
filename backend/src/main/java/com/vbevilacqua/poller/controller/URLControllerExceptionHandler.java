package com.vbevilacqua.poller.controller;

import com.vbevilacqua.poller.service.ErrorResponse;
import com.vbevilacqua.poller.service.InvalidURLException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
@Component
public class URLControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(IOException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "URL Invalid or Malformed");
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(InvalidURLException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "URL not found");
    }
}