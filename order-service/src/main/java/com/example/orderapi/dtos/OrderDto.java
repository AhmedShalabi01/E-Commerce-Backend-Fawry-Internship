package com.example.orderapi.dtos;

import com.example.orderapi.entities.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class OrderDto {
    private String email;
    private long accountNumber;
    private String coupon;
    private int storeId;
    private List<OrderItem> orderItems;
}
