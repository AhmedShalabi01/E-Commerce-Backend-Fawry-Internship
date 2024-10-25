package com.example.couponapi.exceptionhandlers;

import com.example.couponapi.exceptionhandlers.responsebodies.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ConstraintViolationExceptionResponseBody>
    handleFieldsValidationException(ConstraintViolationException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ConstraintViolationExceptionResponseBody body =
                new ConstraintViolationExceptionResponseBody(status, exception);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentNotValidExceptionResponseBody>
    handleDiscountValidationException(BindingResult result) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        MethodArgumentNotValidExceptionResponseBody body =
                new MethodArgumentNotValidExceptionResponseBody(status, result);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<IllegalStateExceptionResponseBody>
    handleConsumptionViolationException(IllegalStateException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        IllegalStateExceptionResponseBody body =
                new IllegalStateExceptionResponseBody(status, exception);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionResponseBody>
    handleEntityNotFoundException(EntityNotFoundException exception) {

        HttpStatusCode status = HttpStatus.NOT_FOUND;
        EntityNotFoundExceptionResponseBody body =
                new EntityNotFoundExceptionResponseBody(status, exception);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<SQLIntegrityConstraintViolationExceptionResponseBody>
    handleSQLIntegrityConstraintViolationException() {

        HttpStatusCode status = HttpStatus.FORBIDDEN;
        SQLIntegrityConstraintViolationExceptionResponseBody body =
                new SQLIntegrityConstraintViolationExceptionResponseBody(status);

        return new ResponseEntity<>(body, status);
    }
}
