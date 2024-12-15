package com.example.financialCalculator.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> HandlerEmployeeAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handlerUserNotFound(UserNotFound userNotFound){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(userNotFound.getMessage());
    }
}
