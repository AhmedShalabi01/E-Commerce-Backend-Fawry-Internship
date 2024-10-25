package com.example.orderapi.services;

import com.example.orderapi.dtos.OrderItemsDto;
import com.example.orderapi.entities.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper{
    @Override
    public OrderItem mapToOrderItemEntity(OrderItemsDto orderItemsDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemsDto.getPrice());
        orderItem.setQuantity(orderItemsDto.getQuantity());
        orderItem.setProductName(orderItemsDto.getProductName());
        orderItem.setProductId(orderItemsDto.getProductId());
        return orderItem;
    }
}
