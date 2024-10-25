package com.example.couponapi.services;

import com.example.couponapi.models.CouponDTO;
import com.example.couponapi.models.CouponForOrderDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CouponService {

    List<CouponDTO> findAllCoupons(Boolean isActive);
    CouponDTO findCouponById(Long id);

    void addCoupon(@Valid CouponDTO couponDTO);
    void deleteCoupon(Long id);
    void updateCoupon(Long id, @Valid CouponDTO couponDTO);
    CouponForOrderDTO consumeCoupon(String couponCode, Long orderId);
    CouponForOrderDTO validate(String couponCode);

}
