package com.example.couponapi.services;

import com.example.couponapi.entities.Consumption;
import com.example.couponapi.entities.Coupon;
import com.example.couponapi.models.CouponDTO;
import com.example.couponapi.models.CouponForOrderDTO;
import com.example.couponapi.models.mappers.CouponMapper;
import com.example.couponapi.repositories.ConsumptionRepository;
import com.example.couponapi.repositories.CouponRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final ConsumptionRepository consumptionRepository;
    private final CouponMapper couponMapper;

    @Override
    public List<CouponDTO> findAllCoupons(Boolean isActive) {
        List<Coupon> coupons = isActive ?
                couponRepository.findAllActiveCoupons() : couponRepository.findAllInactiveCoupons();

        return coupons.stream()
                .map(couponMapper::toCouponDto)
                .collect(Collectors.toList());

    }
    @Override
    public CouponDTO findCouponById(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coupon was not found"));
        return couponMapper.toCouponDto(coupon);
    }

    @Override
    public void addCoupon(@Valid CouponDTO couponDTO) {
        Coupon coupon = couponMapper.toCoupon(couponDTO);
        couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Deletion Failed: coupon was not found"));
        coupon.setActive(false);
        couponRepository.delete(coupon);
    }

    @Override
    public void updateCoupon(Long id, @Valid CouponDTO couponDTO) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Update Failed: coupon was not found"));
        BeanUtils.copyProperties(couponDTO, coupon, "id");
        couponRepository.save(coupon);
    }

    @Override
    public CouponForOrderDTO consumeCoupon(String couponCode, Long orderId) {
        // Check if coupon exists
        Coupon coupon = couponRepository.findByCode(couponCode)
                .orElseThrow(() -> new EntityNotFoundException("Consumption Failed: coupon was not found"));

        // Decrement maximum usages, check coupon expiry date and persist updated coupon
        decrementMaxUsages(coupon);
        checkCouponExpiry(coupon);
        couponRepository.save(coupon);

        // Create consumption entity and persist it
        Consumption consumption = createConsumption(coupon.getId(), orderId);
        consumptionRepository.save(consumption);

        // Send CouponDTO
        return couponMapper.toCouponForOrderDTO(coupon);
    }
    @Override
    public CouponForOrderDTO validate(String couponCode) {
        // Check if coupon exists
        Coupon coupon = couponRepository.findByCode(couponCode)
                .orElseThrow(() -> new EntityNotFoundException("Consumption Failed: coupon was not found"));
        return couponMapper.toCouponForOrderDTO(coupon);
    }

    //----------------------------------------------------------------------------------------------

    private void decrementMaxUsages(Coupon coupon) {
        if (coupon.getMaxUsages() <= 0) {
            throw new IllegalStateException("Coupon has reached its maximum usages");
        }
        coupon.setMaxUsages(coupon.getMaxUsages() - 1);
    }

    private void checkCouponExpiry(Coupon coupon) {
        if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Coupon has expired and cannot be consumed");
        }
    }

    private Consumption createConsumption(Long couponId, Long orderId) {
        Consumption consumption = new Consumption();
        consumption.setCouponId(couponId);
        consumption.setOrderId(orderId);
        consumption.setDateTime(LocalDateTime.now());

        return consumption;
    }

}
