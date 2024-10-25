package com.example.orderapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {
    private int id;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
}
