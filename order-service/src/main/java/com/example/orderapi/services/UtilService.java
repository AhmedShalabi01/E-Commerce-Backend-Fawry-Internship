package com.example.orderapi.services;


import com.example.orderapi.dtos.CouponResponseDto;
import com.example.orderapi.dtos.OrderItemsDto;
import com.example.orderapi.dtos.PostProductDto;
import com.example.orderapi.entities.OrderItem;
import com.example.orderapi.repositories.OrderItemRepo;
import com.example.orderapi.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilService {
    private OrderItemRepo orderItemsRepo;
    private OrderRepo orderRepo;
    private StoreService storeService;
    private BankService bankService;
    private CouponService couponService;
//    private NotificationService notificationService;
    private Mapper mapper;

    public BigDecimal calculateCost(List<PostProductDto> productDtoList){
        return productDtoList.stream()
                .filter(PostProductDto::isInStock)
                .map(product -> {
                    BigDecimal price = BigDecimal.valueOf(product.getPrice());
                    BigDecimal quantity = BigDecimal.valueOf(product.getQuantity());
                    return price.multiply(quantity);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Map<Integer, Integer> produceMapOfIdsAndQuantities(List<OrderItem> orderItems){
        Map<Integer, Integer> mapIdsAndQuantity = new HashMap<>();
        for(OrderItem item : orderItems){
            mapIdsAndQuantity.put(item.getProductId(), item.getQuantity());
        }
        return mapIdsAndQuantity;
    }
    public BigDecimal handleCouponCalculations(CouponResponseDto couponResponseDto, BigDecimal cost){
        String type = couponResponseDto.getType();
        double discount = couponResponseDto.getDiscount();
        BigDecimal discountAsBigDecimal = BigDecimal.valueOf(discount);
        if (type == "FIXED") {
            return cost.doubleValue() > discount ? cost.subtract(discountAsBigDecimal) : BigDecimal.ZERO;
        }
        return cost.subtract(cost.multiply(discountAsBigDecimal).divide(BigDecimal.valueOf(100), RoundingMode.DOWN));
    }
}
