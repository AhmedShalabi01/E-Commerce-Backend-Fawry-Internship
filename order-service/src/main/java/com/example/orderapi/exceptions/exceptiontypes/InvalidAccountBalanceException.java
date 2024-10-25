package com.example.orderapi.exceptions.exceptiontypes;

public class InvalidAccountBalanceException extends RuntimeException{
    public InvalidAccountBalanceException(String message){
        super(message);
    }
}
