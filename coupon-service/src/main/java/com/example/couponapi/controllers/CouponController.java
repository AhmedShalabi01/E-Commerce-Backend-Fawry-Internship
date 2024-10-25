package com.example.couponapi.controllers;

import com.example.couponapi.entities.types.CouponType;
import com.example.couponapi.entities.types.FixedTypeCouponMarker;
import com.example.couponapi.entities.types.PercentageTypeCouponMarker;
import com.example.couponapi.models.CouponDTO;
import com.example.couponapi.models.CouponForOrderDTO;
import com.example.couponapi.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/list")
    public ResponseEntity<List<CouponDTO>> listAllCoupons(
            @RequestParam(value = "active", defaultValue = "true", required = false) Boolean isActive) {

        List<CouponDTO> couponDTOList = couponService.findAllCoupons(isActive);
        return new ResponseEntity<>(couponDTOList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CouponDTO> findCouponById(@PathVariable Long id) {
        CouponDTO couponDTO = couponService.findCouponById(id);
        return new ResponseEntity<>(couponDTO, HttpStatus.OK);
    }

    @PostMapping("/add/fixed")
    public ResponseEntity<Void> addFixedCoupon(@Validated(FixedTypeCouponMarker.class)
                                                   @RequestBody CouponDTO couponDTO) {
        couponDTO.setType(CouponType.FIXED);
        couponService.addCoupon(couponDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/add/percentage")
    public ResponseEntity<Void> addPercentageCoupon(@Validated(PercentageTypeCouponMarker.class)
                                                        @RequestBody CouponDTO couponDTO) {
        couponDTO.setType(CouponType.PERCENTAGE);
        couponService.addCoupon(couponDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/fixed/{id}")
    public ResponseEntity<Void> updateCoupon(@PathVariable Long id,
                                             @Validated(FixedTypeCouponMarker.class)
                                             @RequestBody CouponDTO couponDTO) {
        couponDTO.setType(CouponType.FIXED);
        couponService.updateCoupon(id, couponDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/percentage/{id}")
    public ResponseEntity<Void> updatePercentageCoupon(@PathVariable Long id,
                                                       @Validated(PercentageTypeCouponMarker.class)
                                                       @RequestBody CouponDTO couponDTO) {
        couponDTO.setType(CouponType.PERCENTAGE);
        couponService.updateCoupon(id, couponDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/consume")
    public boolean consumeCoupon(@RequestParam("code") String code,
                                 @RequestParam(name = "orderId") Long orderId) {
        CouponForOrderDTO couponForOrderDTO = couponService.consumeCoupon(code, orderId);
        return couponForOrderDTO != null;
    }
    @GetMapping("/validatecode")
    public CouponForOrderDTO consumeCoupon(@RequestParam("code") String code) {
        CouponForOrderDTO couponForOrderDTO = couponService.validate(code);
        return couponForOrderDTO;
    }
}
