package com.fawry.store.services.serviceimp;

import com.fawry.store.dtos.StockHistoryDto;
import com.fawry.store.entites.Product;
import com.fawry.store.entites.StockHistory;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.repos.StockHistoryRepo;
import com.fawry.store.services.StockHistoryService;
import com.fawry.store.services.mapper.StockHistoryMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Primary
public class StockHistoryServiceImp implements StockHistoryService {

    @Autowired
    StockHistoryRepo repo;

    @Autowired
    StockHistoryMapper mapper;

    final String STOCK_HISTORY_NOT_FOUND = "STOCK_HISTORY_NOT_FOUND";

    @Override
    public List<StockHistoryDto> getAllHistories() {
        return repo.findAll()
                .stream()
                .map(mapper::toStockHistoryDto)
                .toList();
    }

    @Override
    public List<StockHistoryDto> getHistoryOfProduct(long productId) {
        return repo.findStockHistoriesByInventory_Product_Id(productId)
                .stream()
                .map(mapper::toStockHistoryDto)
                .toList();
    }

    @Override
    public List<StockHistoryDto> getHistoryOfWarehouse(long warehouseId) {
        return repo.findStockHistoriesByInventory_Warehouse_Id(warehouseId)
                .stream()
                .map(mapper::toStockHistoryDto)
                .toList();
    }


    @SneakyThrows
    @Override
    public StockHistoryDto getHistory(long id) {
        return mapper.toStockHistoryDto(repo.findById(id)
                .orElseThrow(()-> new NoSuchEntityException(STOCK_HISTORY_NOT_FOUND)));
    }



    @Override
    public void removeHistory(long id) {
        StockHistory history = repo.findById(id).orElseThrow(()-> new NoSuchElementException(STOCK_HISTORY_NOT_FOUND));
        repo.delete(history);
    }
}
