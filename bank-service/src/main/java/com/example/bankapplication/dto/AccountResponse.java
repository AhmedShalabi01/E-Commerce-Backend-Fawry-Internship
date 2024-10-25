package com.example.bankapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponse {

    @JsonProperty("cardnumber")
    private Long cardNumber;
    @JsonProperty("username")
    private String username;
    @JsonProperty("balance")
    private BigDecimal balance;
}
