package com.example.bankapplication.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class ErrorDetails {
    private String dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ISO_DATE_TIME);
    private final String message;
    private final String code;
}
