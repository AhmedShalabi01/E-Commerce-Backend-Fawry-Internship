package com.example.couponapi.repositories;

import com.example.couponapi.entities.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    @Query("SELECT c FROM Consumption c WHERE "
            + "(:couponCode IS NULL OR c.coupon.code = :couponCode) AND "
            + "(:orderId IS NULL OR c.orderId = :orderId) AND "
            + "(:startDate IS NULL OR c.dateTime >= :startDate) AND "
            + "(:endDate IS NULL OR c.dateTime <= :endDate)")

    List<Consumption> findConsumptionsByFilters(
            @Param("couponCode") String couponCode,
            @Param("orderId") Long orderId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
