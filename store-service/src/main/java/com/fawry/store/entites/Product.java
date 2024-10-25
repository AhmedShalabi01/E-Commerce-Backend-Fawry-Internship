package com.fawry.store.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    long id;

    @Column(name = "title")
    String name;

    @Column(name = "price")
    double price;

    @Column(name = "category")
    String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    List<Inventory> inventories = new ArrayList<>();


    public Product(){}

    public Product(long id, String title, double price, String category) {
        this.id = id;
        this.name =title ;
        this.price = price;
        this.categoryName = category;
    }

    public boolean addNewInventory(Inventory inventory){
        return inventories.add(inventory);
    }
}
