package com.fawry.store.services.mapper;

import com.fawry.store.dtos.InventoryDto;
import com.fawry.store.entites.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryDto toInventoryDto (Inventory inventory);
    Inventory toInventory (InventoryDto inventoryDto);
}
