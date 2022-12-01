package com.wipro.UHGVirtualSME.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialException(Exception ex){

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .stackTrace(ex.fillInStackTrace().toString())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);

    }
}
