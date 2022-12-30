package com.services.productservice.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    Map<String,Object> map = new HashMap<>();
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> noSuchElementFoundHandler(NoSuchElementException ex)
    { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getError(ex.getMessage(), false, HttpStatus.NOT_FOUND)); }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Map<String, Object>> invalidFormatHandler(InvalidFormatException ex)
    { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError(ex.getMessage(), false, HttpStatus.BAD_REQUEST)); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private  Map<String,Object> getError(String message, Boolean success, HttpStatus httpStatus)
    {
        map.put("message", message);
        map.put("success", success);
        map.put("status", httpStatus);
        return map;
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors)
    {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
