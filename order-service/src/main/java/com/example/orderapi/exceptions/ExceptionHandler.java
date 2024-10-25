package com.example.orderapi.exceptions;

import com.example.orderapi.exceptions.exceptiontypes.CardNumberNotFoundException;
import com.example.orderapi.exceptions.exceptiontypes.ConsumeCouponException;
import com.example.orderapi.exceptions.exceptiontypes.CouponNotFoundException;
import com.example.orderapi.exceptions.exceptiontypes.ProductOutOfStockException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductOutOfStockException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage productOutOfStock(
            ProductOutOfStockException productNotFoundException, WebRequest webRequest){

        return new ErrorMessage(HttpStatus.NOT_FOUND,
                productNotFoundException.getMessage(),
                webRequest.getDescription(true));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CouponNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage couponNotFound(
            ProductOutOfStockException productNotFoundException, WebRequest webRequest){

        return new ErrorMessage(HttpStatus.NOT_FOUND,
                productNotFoundException.getMessage(),
                webRequest.getDescription(true));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConsumeCouponException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage consumeCouponFailed(
            ProductOutOfStockException productNotFoundException, WebRequest webRequest){

        return new ErrorMessage(HttpStatus.BAD_REQUEST,
                productNotFoundException.getMessage(),
                webRequest.getDescription(true));
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(CardNumberNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage cardNumberInvalid(
            CardNumberNotFoundException cardNumberNotFoundException, WebRequest webRequest){

        return new ErrorMessage(HttpStatus.NOT_FOUND,
                cardNumberNotFoundException.getMessage(),
                webRequest.getDescription(true));
    }
}
