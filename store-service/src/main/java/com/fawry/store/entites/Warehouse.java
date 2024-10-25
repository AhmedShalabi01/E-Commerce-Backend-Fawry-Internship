package com.fawry.store.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "warhouse_name")
    String name;

    @Column(name = "warehouse_location")
    String location;

    @Column(name = "warehouse_address")
    String address;



    @Column(name = "created_at" , updatable = false )
    @Temporal(value = TemporalType.DATE)
    Date date ;


    @OneToMany(mappedBy = "warehouse" , cascade = CascadeType.ALL)
    List<Inventory> inventories = new ArrayList<>();

    public Warehouse(){}

    public Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public boolean createWarehouseInventory(Inventory inventory){
        return inventories.add(inventory);
    }
}
