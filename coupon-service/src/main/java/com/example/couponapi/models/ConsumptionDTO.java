package com.example.couponapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsumptionDTO {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long couponId;

    @JsonProperty("couponCode")
    private String couponCode;

    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("timestamp")
    private LocalDateTime dateTime;

    // For visible output
    @JsonProperty("id")
    public Long getId() {
        return id;
    }
}
