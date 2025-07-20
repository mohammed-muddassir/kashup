package com.koston;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<?> handleKostonExceptions(KostonException exception) throws Exception
    {
        Map<String, Object> errResponse = ApplicationUtil.getResponseMap(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(errResponse, HttpStatus.BAD_REQUEST);
    }
}
