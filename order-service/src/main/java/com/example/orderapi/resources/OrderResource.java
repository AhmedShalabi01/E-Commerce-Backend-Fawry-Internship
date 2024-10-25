package com.example.orderapi.resources;


import com.example.orderapi.dtos.CouponResponseDto;
import com.example.orderapi.dtos.OrderDto;
import com.example.orderapi.dtos.OrderRequestDto;
import com.example.orderapi.dtos.PostProductDto;
import com.example.orderapi.entities.Order;
import com.example.orderapi.repositories.OrderRepo;
import com.example.orderapi.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderResource {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CouponService couponService;

    @GetMapping("/byDateRange")
    public List<Order> getOrdersInDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return orderRepo.findAllByDateBetween(startDate, endDate).orElse(Collections.emptyList());
    }
    @GetMapping("/byEmail")
    public List<Order> getOrdersInEmail(
            @RequestParam String email) {
        return orderRepo.findByEmail(email).orElse(Collections.emptyList());
    }
    @GetMapping
    public List<Order> getAll() {
        return orderRepo.findAll().stream().toList();
    }
    @PostMapping("/orderwithoutcoupon")
    public Order placeOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(
                orderDto.getOrderItems(), orderDto.getEmail(),
                orderDto.getAccountNumber(), orderDto.getStoreId());
    }
    @PostMapping("/orderwithcoupon")
    public Order placeOrderWithCoupon(@RequestBody OrderDto orderDto){
        return orderService.createOrderWithCopoun(
                orderDto.getOrderItems(), orderDto.getEmail(),
                orderDto.getAccountNumber(), orderDto.getCoupon(), orderDto.getStoreId());
    }
    @GetMapping("/getCoupon/{couponCode}")
    public CouponResponseDto getCoupon(@PathVariable String couponCode) {
        return couponService.validateCoupon(couponCode);
    }

}
