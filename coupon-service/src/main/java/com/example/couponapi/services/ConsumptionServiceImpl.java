package com.example.couponapi.services;

import com.example.couponapi.entities.Consumption;
import com.example.couponapi.models.ConsumptionDTO;
import com.example.couponapi.models.mappers.ConsumptionMapper;
import com.example.couponapi.repositories.ConsumptionRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository consumptionRepository;
    private final ConsumptionMapper consumptionMapper;

    @Override
    public List<ConsumptionDTO> findAllConsumptions() {
        return consumptionRepository.findAll().stream()
                .map(consumptionMapper::toConsumptionDto)
                .toList();
    }

    @Override
    public ConsumptionDTO findConsumptionById(Long id) {
        Consumption consumption = consumptionRepository.findById(id).orElseThrow(EntityExistsException::new);
        return consumptionMapper.toConsumptionDto(consumption);
    }

    @Override
    public List<ConsumptionDTO> findAllConsumptionsByFilters(String couponCode, Long orderId,
                                                             LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;

        return consumptionRepository
                .findConsumptionsByFilters(couponCode, orderId, startDateTime, endDateTime)
                .stream()
                .map(consumptionMapper::toConsumptionDto)
                .toList();
    }

    @Override
    public void deleteConsumption(Long id) {
        Consumption consumption = consumptionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        consumptionRepository.delete(consumption);
    }
}
