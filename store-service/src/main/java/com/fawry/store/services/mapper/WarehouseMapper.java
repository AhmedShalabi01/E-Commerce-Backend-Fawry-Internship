package com.fawry.store.services.mapper;


import com.fawry.store.dtos.WarehouseDto;
import com.fawry.store.entites.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse toWarehouse(WarehouseDto warehouseDto);
    WarehouseDto toWarehouseDto(Warehouse warehouse);
}
