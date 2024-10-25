package com.example.couponapi.models;

import com.example.couponapi.entities.types.CouponType;
import com.example.couponapi.entities.types.FixedTypeCouponMarker;
import com.example.couponapi.entities.types.PercentageTypeCouponMarker;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CouponDTO {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Coupon code cannot be null")
    @Size(min = 3, max = 15, message = "Coupon code should have between 3 and 15 characters")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Coupon code should be in capital and/or include numbers")
    @JsonProperty("code")
    private String code;

    @NotNull(message = "Coupon max usages must be defined")
    @PositiveOrZero(message = "Maximum number of usages for a coupon must be positive")
    @Max(value = 100, message = "Maximum usages cannot exceed 100")
    @JsonProperty("maxUsages")
    private Integer maxUsages;

    @NotNull(message = "Coupon must have an expiry date")
    @Future(message = "Chosen expiry date cannot be in the past")
    @JsonProperty("expiryDate")
    private LocalDate expiryDate;

    @NotNull(message = "Discount must have a value")
    @Positive(message = "Fixed Discount cannot be negative")
    @Min(value = 1, message = "Percentage discount should be 1% minimum",
            groups = PercentageTypeCouponMarker.class)
    @Max(value = 100, message = "Percentage discount cannot exceed 100%",
            groups = PercentageTypeCouponMarker.class)
    @Min(value = 1, message = "Fixed discount must be at least $1",
            groups = FixedTypeCouponMarker.class)
    @JsonProperty("discount")
    private Double discount;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private CouponType type;

    @JsonIgnore
    private Boolean active = true;

    // For visible output
    @JsonProperty("id")
    public Long getId() {
        return this.id;
    }
    @JsonProperty("type")
    public CouponType getType() {
        return this.type;
    }
}
