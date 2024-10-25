package com.example.orderapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CouponResponseDto {
    @JsonProperty("discount")
    private Double discount;
    @JsonProperty("type")
    private String type;

}
