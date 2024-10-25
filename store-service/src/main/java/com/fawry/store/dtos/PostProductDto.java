package com.fawry.store.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostProductDto {
    long id;
    String name;
    double price;
    long quantity;
    boolean inStock;

}
