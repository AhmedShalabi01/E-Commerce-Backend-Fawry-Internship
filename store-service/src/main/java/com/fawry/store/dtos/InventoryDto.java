package com.fawry.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fawry.store.entites.Product;
import com.fawry.store.entites.Warehouse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class InventoryDto {

    long id;

    long productQuantity;

    Date date;

    @JsonIgnore
    Warehouse warehouse;

    Product product;
}
