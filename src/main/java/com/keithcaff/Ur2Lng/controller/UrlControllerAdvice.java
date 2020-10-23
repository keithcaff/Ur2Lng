package com.keithcaff.Ur2Lng.controller;

import com.keithcaff.Ur2Lng.exceptions.UrlNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = UrlController.class)
public class UrlControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> urlNotFoundException(final UrlNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String allErrors = exception.getBindingResult().getAllErrors().stream().
                map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        return new ResponseEntity<>(allErrors, headers, status);
    }
}
