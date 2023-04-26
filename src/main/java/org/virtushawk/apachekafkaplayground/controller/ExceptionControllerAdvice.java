package org.virtushawk.apachekafkaplayground.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.virtushawk.apachekafkaplayground.entity.exception.ValidationException;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException exception, WebRequest webRequest) {
        String message = exception.getMessage();
        return handleExceptionInternal(exception,message,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
