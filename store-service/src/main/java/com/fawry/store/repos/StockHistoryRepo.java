package com.fawry.store.repos;

import com.fawry.store.entites.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockHistoryRepo extends JpaRepository<StockHistory , Long> {
    List<StockHistory> findStockHistoriesByInventory_Product_Id(long productId);
    List<StockHistory> findStockHistoriesByInventory_Warehouse_Id(long warehouseId);

}
