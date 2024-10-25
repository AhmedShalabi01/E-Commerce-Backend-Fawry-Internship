package com.fawry.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDtoData {
    long id;

    String name;

    double price;

    String categoryName;

    String imageUrl;
    @JsonIgnore
    String categoryId;
}
