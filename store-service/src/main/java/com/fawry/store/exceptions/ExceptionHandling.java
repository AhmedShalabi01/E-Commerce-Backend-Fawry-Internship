package com.fawry.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<?> noSuchEntityException(NoSuchEntityException exception , WebRequest webRequest){
        ErrorMessage errorDetails = new ErrorMessage(
                HttpStatus.NOT_FOUND.value() ,
                exception.getMessage() ,
                new Date() ,
                webRequest.getDescription(true)
        );
        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsumeProductException.class)
    ResponseEntity<?> consumeProductExceptionHandling(ConsumeProductException exception , WebRequest webRequest){
        ErrorMessage errorDetails = new ErrorMessage(
                HttpStatus.NOT_FOUND.value() ,
                exception.getMessage() ,
                new Date() ,
                webRequest.getDescription(true)
        );
        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<?> globalExceptionHandling(Exception exception , WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage() ,
                new Date() ,
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
