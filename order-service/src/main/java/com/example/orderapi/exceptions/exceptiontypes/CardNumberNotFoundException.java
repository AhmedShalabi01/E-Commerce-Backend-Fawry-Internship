package com.example.orderapi.exceptions.exceptiontypes;

public class CardNumberNotFoundException extends RuntimeException{
    public CardNumberNotFoundException(String message){
        super(message);
    }
}
