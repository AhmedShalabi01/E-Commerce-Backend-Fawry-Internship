package com.example.couponapi.entities;

import com.example.couponapi.entities.types.CouponType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@SQLDelete(sql = "UPDATE coupons SET active = false WHERE id=?")
@Data
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer maxUsages;
    private LocalDate expiryDate;
    private Double discount;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    private Boolean active = true;
}
