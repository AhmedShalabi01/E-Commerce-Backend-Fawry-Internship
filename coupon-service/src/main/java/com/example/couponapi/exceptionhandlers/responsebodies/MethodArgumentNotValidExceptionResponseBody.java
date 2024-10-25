package com.example.couponapi.exceptionhandlers.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MethodArgumentNotValidExceptionResponseBody {
    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private List<String> errorMessages;

    public MethodArgumentNotValidExceptionResponseBody(HttpStatusCode status, BindingResult result) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessages = formatErrorMessage(result);
    }

    private List<String> formatErrorMessage(BindingResult result) {
        return result.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }
}
