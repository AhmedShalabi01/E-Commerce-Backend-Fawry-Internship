package com.fawry.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fawry.store.entites.Inventory;
import com.fawry.store.entites.enums.StockEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StockHistoryDto {

    long id;

    String productName;

    String warehouseName;

    long quantity;

    StockEnum stockEnum;

    Date date;

    Date time;
    @JsonIgnore
    Inventory inventory;
}
