package com.spring_boot.online.book.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponse> categoryNotFoundExceptionHandler (CategoryNotFoundException exception, WebRequest webRequest){

        ExceptionResponse exceptionResponse =  new ExceptionResponse(
        webRequest.getDescription(false),
        HttpStatus.CONFLICT,
        exception.getMessage(),
        LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);

    }
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> bookNotFoundExceptionHandler (BookNotFoundException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);

    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> categoryAlreadyExistExceptionHandler (CategoryAlreadyExistException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegealArgumentExceptionHandler (IllegalArgumentException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);

    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> ExceptionHandler (Exception exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);

    }



}
