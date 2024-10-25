package com.example.couponapi.models.mappers;

import com.example.couponapi.entities.Coupon;
import com.example.couponapi.models.CouponDTO;
import com.example.couponapi.models.CouponForOrderDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CouponMapper {
    Coupon toCoupon(CouponDTO couponDTO);
    CouponDTO toCouponDto(Coupon coupon);
    CouponForOrderDTO toCouponForOrderDTO(Coupon coupon);
}
