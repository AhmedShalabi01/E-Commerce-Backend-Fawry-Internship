package com.fawry.store.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class ErrorMessage {
    private int code;
    private String message;
    private Date timeStamp;

    private String desc;

    public ErrorMessage(int code, String message, Date timeStamp, String desc) {
        this.code = code;
        this.message = message;
        this.timeStamp = timeStamp;
        this.desc = desc;
    }

}
