package com.fawry.store.dtos;

import com.fawry.store.entites.Inventory;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProductDto {

    long id;

    String name;

    double price;


    String categoryName;
}
