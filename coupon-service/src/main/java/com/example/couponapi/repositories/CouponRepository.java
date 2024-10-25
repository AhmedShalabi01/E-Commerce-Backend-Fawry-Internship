package com.example.couponapi.repositories;

import com.example.couponapi.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.active=true")
    List<Coupon> findAllActiveCoupons();

    @Query("SELECT c FROM Coupon c WHERE c.active=false")
    List<Coupon> findAllInactiveCoupons();

    Optional<Coupon> findByCode(String code);
}
