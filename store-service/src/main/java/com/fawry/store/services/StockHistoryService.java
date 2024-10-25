package com.fawry.store.services;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.dtos.StockHistoryDto;
import com.fawry.store.entites.StockHistory;

import java.util.List;

public interface StockHistoryService {

    List<StockHistoryDto> getAllHistories();
    List<StockHistoryDto> getHistoryOfProduct(long productId);
    List<StockHistoryDto> getHistoryOfWarehouse(long warehouseId);
    StockHistoryDto getHistory(long id);
    void removeHistory(long id);
}
