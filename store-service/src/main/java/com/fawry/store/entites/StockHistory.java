package com.fawry.store.entites;

import com.fawry.store.entites.enums.StockEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "stock_hisotry")
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "warehouse_name")
    String warehouseName;

    @Column(name = "quantity")
    long quantity;

    @Column(name = "stock_action")
    @Enumerated(EnumType.STRING)
    StockEnum stockEnum;

    @Column(name = "date" , updatable = false)
    @Temporal(TemporalType.DATE)
    Date date = new Date();

    @Column(name = "time" , updatable = false)
    @Temporal(TemporalType.TIME)
    Date time = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id")
    Inventory inventory;


    public StockHistory() {}


    public StockHistory(String productName, String warehouseName, long quantity, StockEnum stockEnum, Date date, Date time) {
        this.productName = productName;
        this.warehouseName = warehouseName;
        this.quantity = quantity;
        this.stockEnum = stockEnum;
        this.date = date;
        this.time = time;
    }
}
