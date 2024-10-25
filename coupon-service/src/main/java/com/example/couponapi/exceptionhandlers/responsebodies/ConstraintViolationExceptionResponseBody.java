package com.example.couponapi.exceptionhandlers.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConstraintViolationExceptionResponseBody {

    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private List<String> errorMessages;


    public ConstraintViolationExceptionResponseBody(HttpStatusCode status, ConstraintViolationException exception) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessages = formatErrorMessage(exception);
    }

    public static List<String> formatErrorMessage(ConstraintViolationException exception) {
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        for (ConstraintViolation<?> violation : constraintViolations) {
            String message = violation.getMessage();
            String[] parts = message.split(",");
            if (parts.length > 0) {
                String lastPart = parts[parts.length - 1].trim();
                errorMessages.add(lastPart);
            }
        }
        return errorMessages;
    }
}
