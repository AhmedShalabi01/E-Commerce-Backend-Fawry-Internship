package com.example.userapi.exception;

public class CustomException extends RuntimeException{
    public CustomException(ExceptionMessage exceptionMessage){
        super(exceptionMessage.message);
    }
}
