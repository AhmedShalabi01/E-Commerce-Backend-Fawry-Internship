package com.example.couponapi.models.mappers;

import com.example.couponapi.entities.Consumption;
import com.example.couponapi.entities.Coupon;
import com.example.couponapi.models.ConsumptionDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-28T22:46:16+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ConsumptionMapperImpl implements ConsumptionMapper {

    @Override
    public ConsumptionDTO toConsumptionDto(Consumption consumption) {
        if ( consumption == null ) {
            return null;
        }

        ConsumptionDTO consumptionDTO = new ConsumptionDTO();

        consumptionDTO.setCouponCode( consumptionCouponCode( consumption ) );
        consumptionDTO.setId( consumption.getId() );
        consumptionDTO.setOrderId( consumption.getOrderId() );
        consumptionDTO.setDateTime( consumption.getDateTime() );

        return consumptionDTO;
    }

    private String consumptionCouponCode(Consumption consumption) {
        if ( consumption == null ) {
            return null;
        }
        Coupon coupon = consumption.getCoupon();
        if ( coupon == null ) {
            return null;
        }
        String code = coupon.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }
}
