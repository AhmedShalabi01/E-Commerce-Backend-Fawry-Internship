package com.example.couponapi.models;

import com.example.couponapi.entities.types.CouponType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CouponForOrderDTO {

    @JsonProperty("discount")
    private Double discount;

    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    private CouponType type;
}
