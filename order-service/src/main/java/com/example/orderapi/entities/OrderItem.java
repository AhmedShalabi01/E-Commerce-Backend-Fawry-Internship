package com.example.orderapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String productName;
    private double price;
    private int quantity;
    @Override
    public String toString(){
        return  "product name: " + productName + " and\n" +
                "product price: " + price + " and\n" +
                "product quantity: " + quantity + "\n";

    }
}
