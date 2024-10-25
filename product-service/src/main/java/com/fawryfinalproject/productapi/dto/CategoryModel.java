package com.fawryfinalproject.productapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryModel {
    public CategoryModel() {
    }
    public CategoryModel(String name) {
        this.name = name;
    }

    private int categoryId;

    @NotBlank(message = "Category name cannot be blank")
    @JsonProperty("categoryName")
    private String name;


}
