package com.example.userapi.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity handelElementNotFound(CustomException customException){
        ApiError apiError = new ApiError(400,customException.getMessage(),new Date());
        return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handelFieldValidations(Exception exception){
        ApiError apiError = new ApiError(400, exception.getMessage(), new Date());
        return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
    }

}
