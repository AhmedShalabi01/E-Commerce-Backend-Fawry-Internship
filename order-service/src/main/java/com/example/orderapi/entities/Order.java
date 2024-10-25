package com.example.orderapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String coupon;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int storeId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Override
    public String toString(){
        return coupon != null ? "order placed successfully\n" +
                "with coupon: " + coupon + " and \n" +
                "from warehouse: " + storeId + " and \n" +
                "order items: " + orderItems :
                "order placed successfully\n" +
                        "from warehouse: " + storeId + " and \n" +
                        "order items: " + orderItems + "\n" ;

    }

}
