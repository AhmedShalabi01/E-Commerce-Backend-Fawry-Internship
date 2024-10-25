package com.fawryfinalproject.productapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fawryfinalproject.productapi.entity.CategoryEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductModel {
    public ProductModel(Long id, String name, double price, String imageUrl, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public ProductModel() {
    }

    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @Min(value = 1,message ="Product price cannot be zero or less" )
    private double price;

    @Min(value = 1, message = "Category ID must be a positive number")
    @NotNull
    private int categoryId;

    private String categoryName;

    @NotBlank(message = "Image link cannot be blank")
    private String imageUrl;

    @JsonIgnore
    private CategoryEntity category;
}
