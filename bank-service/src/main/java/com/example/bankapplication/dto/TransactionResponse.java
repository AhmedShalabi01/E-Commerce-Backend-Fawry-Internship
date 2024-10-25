package com.example.bankapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("cardnumber")
    private Long cardNumber;
    @JsonProperty("timestamp")
    private LocalDate timestamp;
    @JsonProperty("type")
    private String type;
    @JsonProperty("amount")
    private BigDecimal amount;
}
