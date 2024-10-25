package com.example.orderapi.exceptions.exceptiontypes;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String message){
        super(message);
    }
}
