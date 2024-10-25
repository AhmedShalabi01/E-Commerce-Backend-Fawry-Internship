package com.fawry.store.repos;

import com.fawry.store.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product , Long> {

}
