package com.example.orderapi.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorMessage {
    private HttpStatus code;
    private String message;
    private String desc;

    public ErrorMessage(HttpStatus code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }


}
