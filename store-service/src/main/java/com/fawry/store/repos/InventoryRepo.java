package com.fawry.store.repos;

import com.fawry.store.entites.Inventory;
import com.fawry.store.entites.Product;
import com.fawry.store.entites.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory , Long> {
    @Query(value = "select * from inventory  where warehouse_id = :warId and product_id = :productId" , nativeQuery = true)
    Optional<Inventory> findProductQuantity(@Param("warId") long warhouseId , @Param("productId") long productId);

    Optional<Inventory> findInventoryByProduct_IdAndWarehouse_Id(long war , long prod);
}
