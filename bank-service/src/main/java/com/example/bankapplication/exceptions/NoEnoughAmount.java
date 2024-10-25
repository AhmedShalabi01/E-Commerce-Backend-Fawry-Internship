package com.example.bankapplication.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
public class NoEnoughAmount extends RuntimeException{
    public NoEnoughAmount(String message) {
        super(message);
    }

}
