package com.fawry.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "product_quantity" , columnDefinition = "int default 0")
    long productQuantity;

    @Column(name = "created_at" , updatable = false)
    @Temporal(value = TemporalType.DATE)
    Date date;


    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    Warehouse warehouse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product product;

    @OneToMany(mappedBy = "inventory" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    List<StockHistory> histories = new ArrayList<>();

    public Inventory(){}

    public Inventory(Date date, long productQuantity) {
        this.date = date;
        this.productQuantity = productQuantity;
    }

    public  Inventory(long productQuantity){
        this.productQuantity = productQuantity;
    }

    public boolean addHistory(StockHistory history){
        return histories.add(history);
    }
}
