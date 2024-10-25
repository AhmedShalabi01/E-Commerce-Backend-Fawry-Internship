package com.example.bankapplication.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NoEnoughAmount.class)
    public ResponseEntity<ErrorDetails> handleNoEnoughAmount(NoEnoughAmount ex) {
        ErrorDetails err = new ErrorDetails(ex.getMessage(),"403");
        return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorDetails err = new ErrorDetails(ex.getMessage(),"404");
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleNegativeMoneyAmount(IllegalArgumentException ex) {
        ErrorDetails err = new ErrorDetails(ex.getMessage(),"400");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails>
    handleFieldsValidationException(ConstraintViolationException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ErrorDetails body = new ErrorDetails(exception.getMessage(), status.toString());
        return new ResponseEntity<>(body, status);
    }
}
