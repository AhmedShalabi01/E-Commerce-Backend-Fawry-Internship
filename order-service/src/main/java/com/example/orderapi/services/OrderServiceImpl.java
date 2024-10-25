package com.example.orderapi.services;

import com.example.orderapi.dtos.CouponResponseDto;
import com.example.orderapi.dtos.EmailDto;
import com.example.orderapi.dtos.OrderItemsDto;
import com.example.orderapi.dtos.PostProductDto;
import com.example.orderapi.entities.Order;
import com.example.orderapi.entities.OrderItem;
import com.example.orderapi.repositories.OrderItemRepo;
import com.example.orderapi.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    @Autowired
    private UtilService utilService;
    @Autowired
    private OrderItemRepo orderItemsRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private StoreService storeService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CouponService couponService;
//    @Autowired
//    private NotificationService notificationService;
    @Autowired
    private Mapper mapper;
    @Override
    public Order createOrder(
            List<OrderItem> orderItems, String email,
            long accountNumber, int storeId) {

        Map<Integer, Integer> mapIdsAndQuantity = utilService.produceMapOfIdsAndQuantities(orderItems);

        List<PostProductDto> productDtoList = storeService
                .validateProductsInStore(mapIdsAndQuantity, storeId);

        BigDecimal cost = utilService.calculateCost(productDtoList);

        if (!bankService.consumeTransfer(accountNumber, cost)){
            throw new RuntimeException("PAYMENT failed");
        }

        if (!storeService.consumeProductsFromWarehouse(mapIdsAndQuantity, storeId)){
            throw new RuntimeException("Consume Coupon failed");
        }

        Order order = new Order();
        order.setStoreId(storeId);
        order.setOrderItems(orderItems);
        order.setDate(LocalDate.now());
        order.setEmail(email);
        order = orderRepo.save(order);
//        notificationService.send(new EmailDto(email, "order placed", order.toString()));
        return order;
    }

    @Override
    public Order createOrderWithCopoun(
            List<OrderItem> orderItems, String email,
            long accountNumber, String coupon, int storeId) {

        CouponResponseDto couponResponseDto = couponService.validateCoupon(coupon);

        Map<Integer, Integer> mapIdsAndQuantity = utilService.produceMapOfIdsAndQuantities(orderItems);

        List<PostProductDto> productDtoList = storeService
                .validateProductsInStore(mapIdsAndQuantity, storeId);

        BigDecimal cost = utilService.calculateCost(productDtoList);

        cost = utilService.handleCouponCalculations(couponResponseDto, cost);

        if (!bankService.consumeTransfer(accountNumber, cost)){
            throw new RuntimeException("PAYMENT failed");
        }

        Order order = new Order();
        order.setStoreId(storeId);
        order.setOrderItems(orderItems);
        order.setDate(LocalDate.now());
        order.setCoupon(coupon);
        order.setEmail(email);
        order = orderRepo.save(order);

        if (!storeService.consumeProductsFromWarehouse(mapIdsAndQuantity, storeId)){
            throw new RuntimeException("Consume from Store failed");
        }

        if (!couponService.consumeCoupon(coupon, order.getId())){
            throw new RuntimeException("Consume Coupon failed");
        }
//        notificationService.send(new EmailDto(email, "order placed", order.toString()));
        return order;
    }

}
