package com.example.orderapi.exceptions.exceptiontypes;

public class ProductOutOfStockException extends RuntimeException{
    public ProductOutOfStockException(String message){
        super(message);
    }
}
