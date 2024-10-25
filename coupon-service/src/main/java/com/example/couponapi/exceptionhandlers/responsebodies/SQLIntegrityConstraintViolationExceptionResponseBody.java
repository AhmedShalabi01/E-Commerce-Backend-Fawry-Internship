package com.example.couponapi.exceptionhandlers.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SQLIntegrityConstraintViolationExceptionResponseBody {
    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private String errorMessage;

    public SQLIntegrityConstraintViolationExceptionResponseBody(HttpStatusCode status) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessage = "Coupon Already Exists";
    }
}
