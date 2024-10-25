package com.example.orderapi.services;

import com.example.orderapi.dtos.OrderItemsDto;
import com.example.orderapi.dtos.OrderRequestDto;
import com.example.orderapi.entities.Order;
import com.example.orderapi.entities.OrderItem;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface OrderService {
    Order createOrder(List<OrderItem> orderItems, String email, long AccountNumber, int storeId);
    Order createOrderWithCopoun(List<OrderItem> orderItems, String email, long AccountNumber, String coupon, int storeId);
}
