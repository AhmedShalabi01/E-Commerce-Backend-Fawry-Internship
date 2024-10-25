package com.example.userapi.exception;

import java.util.Date;

public class ApiError {

    private Integer errorCode;
    private String message;
    private Date date;


    public ApiError(Integer errorCode, String message, Date date) {
        this.errorCode = errorCode;
        this.message = message;
        this.date = date;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

