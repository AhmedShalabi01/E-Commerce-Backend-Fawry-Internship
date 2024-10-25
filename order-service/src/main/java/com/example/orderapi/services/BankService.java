package com.example.orderapi.services;

import com.example.orderapi.exceptions.exceptiontypes.CardNumberNotFoundException;
import com.example.orderapi.exceptions.exceptiontypes.InvalidAccountBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class BankService {
    @Autowired
    private WebClient webClient;
    String url = "http://localhost:8083/bank";

    public boolean consumeTransfer(long customerCard, BigDecimal amount){
        long val = 8508928375820175L;
        return Boolean.TRUE.equals(webClient.put()
                .uri(url + "/transfer", uriBuilder -> uriBuilder
                        .queryParam("customerCard", customerCard)
                        .queryParam("merchantCard", val)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.BAD_REQUEST){
                        return Mono.error(new IllegalArgumentException(response.toString()));
                    } else if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new CardNumberNotFoundException(response.toString()));
                    } else {
                        return Mono.error(new InvalidAccountBalanceException(response.toString()));
                    }
                })
                .bodyToMono(Boolean.class)
                .block());
    }
}
