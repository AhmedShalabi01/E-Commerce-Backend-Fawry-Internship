package com.example.couponapi.models.mappers;

import com.example.couponapi.entities.Coupon;
import com.example.couponapi.models.CouponDTO;
import com.example.couponapi.models.CouponForOrderDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-28T22:46:15+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CouponMapperImpl implements CouponMapper {

    @Override
    public Coupon toCoupon(CouponDTO couponDTO) {
        if ( couponDTO == null ) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setId( couponDTO.getId() );
        coupon.setCode( couponDTO.getCode() );
        coupon.setMaxUsages( couponDTO.getMaxUsages() );
        coupon.setExpiryDate( couponDTO.getExpiryDate() );
        coupon.setDiscount( couponDTO.getDiscount() );
        coupon.setType( couponDTO.getType() );
        coupon.setActive( couponDTO.getActive() );

        return coupon;
    }

    @Override
    public CouponDTO toCouponDto(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        CouponDTO couponDTO = new CouponDTO();

        couponDTO.setId( coupon.getId() );
        couponDTO.setCode( coupon.getCode() );
        couponDTO.setMaxUsages( coupon.getMaxUsages() );
        couponDTO.setExpiryDate( coupon.getExpiryDate() );
        couponDTO.setDiscount( coupon.getDiscount() );
        couponDTO.setType( coupon.getType() );
        couponDTO.setActive( coupon.getActive() );

        return couponDTO;
    }

    @Override
    public CouponForOrderDTO toCouponForOrderDTO(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        CouponForOrderDTO couponForOrderDTO = new CouponForOrderDTO();

        couponForOrderDTO.setDiscount( coupon.getDiscount() );
        couponForOrderDTO.setType( coupon.getType() );

        return couponForOrderDTO;
    }
}
