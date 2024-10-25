package com.example.couponapi.models.mappers;

import com.example.couponapi.entities.Consumption;
import com.example.couponapi.models.ConsumptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ConsumptionMapper {
    @Mapping(target = "couponId", ignore = true)
    @Mapping(source = "coupon.code", target = "couponCode")
    ConsumptionDTO toConsumptionDto(Consumption consumption);
}
