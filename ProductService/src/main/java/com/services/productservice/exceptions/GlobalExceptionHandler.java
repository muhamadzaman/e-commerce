package com.services.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    Map<String,Object> map = new HashMap<>();
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> notFoundHandler(NoSuchElementException ex)
    { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getError(ex.getMessage(), false, HttpStatus.NOT_FOUND)); }

    private  Map<String,Object> getError(String message, Boolean success, HttpStatus httpStatus)
    {
        map.put("message", message);
        map.put("success", success);
        map.put("status", httpStatus);
        return map;
    }
}
