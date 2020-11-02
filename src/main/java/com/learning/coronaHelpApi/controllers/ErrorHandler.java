package com.learning.coronaHelpApi.controllers;

import com.learning.coronaHelpApi.Exceptions.HelperNotFoundException;
import com.learning.coronaHelpApi.models.ErrorFieldMessage;
import com.learning.coronaHelpApi.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(HelperNotFoundException.class)
    ResponseEntity<ErrorMessage> HelperNotFoundExceptionHandler(HelperNotFoundException e) {
       return new ResponseEntity<>(new ErrorMessage(404, e.getMessage(), e.getTip()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<ErrorFieldMessage>> MethodArguementNotValidexceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fielderrors = e.getBindingResult().getFieldErrors();
        List<ErrorFieldMessage> errorFieldMessages = (List<ErrorFieldMessage>) fielderrors
                .stream()
                .map(fieldError -> new ErrorFieldMessage(fieldError.getCode(), fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(errorFieldMessages, HttpStatus.BAD_REQUEST);
    }
}

