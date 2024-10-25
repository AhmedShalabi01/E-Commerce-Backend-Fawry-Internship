package com.fawryfinalproject.productapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "category")
public class CategoryEntity {
    public CategoryEntity(String name) {
        this.name = name;
    }
    public CategoryEntity() {

    }

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String name;



}