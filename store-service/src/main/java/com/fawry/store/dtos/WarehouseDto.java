package com.fawry.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fawry.store.entites.Inventory;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class WarehouseDto {

    long id;

    String name;

    Date date;

    String location;

    String address;
    @JsonIgnore
    List<Inventory> inventories;
}
