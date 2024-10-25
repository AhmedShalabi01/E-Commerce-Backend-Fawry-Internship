package com.fawry.store.services;

import com.fawry.store.dtos.InventoryDto;
import com.fawry.store.dtos.ProductDto;

import java.util.List;

public interface InventoryService {

    List<InventoryDto> getAllInventories();
    InventoryDto getInventoryById(long id);
    InventoryDto createInventory(InventoryDto inventoryDto);
    InventoryDto updateInventory(long id , InventoryDto inventoryDto);
    void destroyInventory(long id);
}
