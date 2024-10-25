package com.example.couponapi.services;

import com.example.couponapi.models.ConsumptionDTO;

import java.time.LocalDate;
import java.util.List;

public interface ConsumptionService {

    List<ConsumptionDTO> findAllConsumptions();
    ConsumptionDTO findConsumptionById(Long id);
    List<ConsumptionDTO> findAllConsumptionsByFilters(String couponCode, Long orderId,
                                                      LocalDate startDate, LocalDate endDate);
    void deleteConsumption(Long id);
}
