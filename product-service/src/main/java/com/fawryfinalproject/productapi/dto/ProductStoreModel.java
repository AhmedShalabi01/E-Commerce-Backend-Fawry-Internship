package com.fawryfinalproject.productapi.dto;


import lombok.Data;

@Data
public class ProductStoreModel {

    private Long id;

    private String name;

    private double price;

    private String categoryName;

    private String imageUrl;

}
