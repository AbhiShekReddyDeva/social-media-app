package com.example.social_media_app.exceptions;

import com.example.social_media_app.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateUserException e){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setTimeStamp(LocalDate.now());
        response.setErrorCode(HttpStatus.CONFLICT);
        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }
}
