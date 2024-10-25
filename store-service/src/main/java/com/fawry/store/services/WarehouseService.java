package com.fawry.store.services;

import com.fawry.store.dtos.*;

import java.util.List;
import java.util.Map;

public interface WarehouseService {
    List<WarehouseDto> getAllWarehouses();
    WarehouseDto getWarehouse(long id);
    WarehouseDto createWarehouse( WarehouseDto dto);
    WarehouseDto updateWarehouse(long id , WarehouseDto dto);
    void removeWarehouse(long id);
    List<String> getAllLocationsNames();
    List<InventoryDto> getAllInventoriesOfWarehouse(long id);
    InventoryDto stockProduct(long warehouseId , long productId , long quantity);

    PostProductDtoQuantity getProductQuantity(long warehouseId , long productId);
    List<PostProductDtoQuantity> consumeProducts(long warehouseId , Map<Integer , Integer> IdsQuantity);
    List<PostProductDto> checkStockOfConsumedProduct(long warehouseId , Map<Integer , Integer> idsQuantity);
    PostProductDtoQuantity consumeProduct(long warehouseId , long productId , long consumeQuan);
    List<ProductDtoData> getProductsOfWarehouse(long warehouseId);
    List<ProductDtoData> getSearchedProductsOfWarehouse(long warehouseId , String text);

}