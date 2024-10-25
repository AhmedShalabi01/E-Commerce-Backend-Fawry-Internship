package com.fawry.store.services.serviceimp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.store.dtos.*;
//import com.fawry.store.entites.Product;
import com.fawry.store.dtos.enums.ProductDtoEnum;
import com.fawry.store.entites.Inventory;
import com.fawry.store.entites.Product;
import com.fawry.store.entites.StockHistory;
import com.fawry.store.entites.Warehouse;
import com.fawry.store.entites.enums.StockEnum;
import com.fawry.store.exceptions.ConsumeProductException;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.externalapi.FetchProductData;
import com.fawry.store.repos.WarehouseRepo;
import com.fawry.store.services.WarehouseService;
import com.fawry.store.services.mapper.InventoryMapper;
import com.fawry.store.services.mapper.ProductMapper;
import com.fawry.store.services.mapper.StockHistoryMapper;
import com.fawry.store.services.mapper.WarehouseMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class WarehouseServiceImp implements WarehouseService {
    @Autowired
    WarehouseRepo repo;
    @Autowired
    WarehouseMapper mapper;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    StockHistoryMapper historyMapper;
    @Autowired
    FetchProductData productData;
    final String WAREHOUSE_NOT_FOUND = "WAREHOUSE_NOT_FOUND";
    final String QUANTITY_GREATER_THAN_STOCK = "QUANTITY_GREATER_THAN_STOCK";

    final String PRODUCT_OUT_STOCK = "PRODUCT_OUT_STOCK";
    final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";

    @Override
    public List<WarehouseDto> getAllWarehouses() {
        return repo.findAll()
                .stream()
                .map(mapper::toWarehouseDto)
                .toList();
    }


    @SneakyThrows
    @Override
    public WarehouseDto getWarehouse(long id) {
        return mapper.toWarehouseDto(repo.findById(id)
                .orElseThrow(()-> new NoSuchEntityException(WAREHOUSE_NOT_FOUND)));
    }


    @Override
    public WarehouseDto createWarehouse(WarehouseDto dto) {
        dto.setDate(new Date());
        return mapper.toWarehouseDto(repo.save(mapper.toWarehouse(dto)));
    }


    @Override
    public WarehouseDto updateWarehouse(long id, WarehouseDto dto) {
        Warehouse warehouse = repo.findById(id).orElseThrow(()-> new NoSuchElementException(WAREHOUSE_NOT_FOUND));

        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setAddress(dto.getAddress());

        return mapper.toWarehouseDto(repo.save(warehouse));
    }


    @Override
    public void removeWarehouse(long id) {
        Warehouse warehouse = repo.findById(id).orElseThrow(()-> new NoSuchElementException(WAREHOUSE_NOT_FOUND));
        repo.delete(warehouse);
    }


    @Override
    public List<String> getAllLocationsNames() {
        return repo.findAll()
                .stream()
                .map(Warehouse::getLocation)
                .toList();
    }




    @Override
    public List<InventoryDto> getAllInventoriesOfWarehouse(long id) {
        return repo.findById(id)
                .stream()
                .flatMap(warehouse -> warehouse.getInventories().stream())
                .map(inventoryMapper::toInventoryDto)
                .toList();
    }


    @Override
    public InventoryDto stockProduct(long warehouseId, long productId, long quantity) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));

        List<Inventory> warehouseInventory =  getInventoryOfProduct(warehouse.getInventories() , productId);

        if(warehouseInventory.isEmpty()){

            ProductDto productDto = (ProductDto) productData.fetchProduct(ProductDtoEnum.GET , productId);


            Product product = productMapper.toProduct(productDto);
            Inventory inventory = new Inventory(new Date() , quantity);
            inventory.setProduct(product);
            inventory.setWarehouse(warehouse);
            product.addNewInventory(inventory);
            warehouse.createWarehouseInventory(inventory);
            StockHistory history = makeStockHistory(inventory , quantity);
            inventory.addHistory(history);

            repo.save(warehouse);

            return inventoryMapper.toInventoryDto(inventory);
        }

        Inventory inventory = warehouseInventory.get(0);
        inventory.setProductQuantity(inventory.getProductQuantity() + quantity);
        StockHistory history = makeStockHistory(inventory , quantity);
        inventory.addHistory(history);

        repo.save(warehouse);

        return inventoryMapper.toInventoryDto(inventory);
    }

    @Override
    public PostProductDtoQuantity getProductQuantity(long warehouseId , long productId) {
        Warehouse warehouse = repo.findById(warehouseId)
                .orElseThrow(()-> new NoSuchEntityException(WAREHOUSE_NOT_FOUND));

        List<Inventory> products = warehouse.getInventories()
                .stream()
                .filter(inventory -> inventory.getProduct().getId() == productId)
                .toList();
        if(products.isEmpty()){
            throw new NoSuchEntityException(PRODUCT_NOT_FOUND);
        }

        Product product = products.get(0).getProduct();

        PostProductDtoQuantity productDtoQuantity = new PostProductDtoQuantity();
        productDtoQuantity.setId(product.getId());
        productDtoQuantity.setName(product.getName());
        productDtoQuantity.setQuantity(products.get(0).getProductQuantity());

        return productDtoQuantity;
    }


    @Override
    public List<PostProductDtoQuantity> consumeProducts(long warehouseId, Map<Integer, Integer> IdsQuantity) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));
        List<Inventory> inventories = warehouse.getInventories();

        List<PostProductDtoQuantity> consumedProducts = new ArrayList<>();

        for ( Map.Entry<Integer , Integer> map : IdsQuantity.entrySet()) {
            consumedProducts.add(getConsumedProduct(warehouse , inventories , map.getKey() , map.getValue()));
        }
        return consumedProducts;
    }

    @Override
    public List<PostProductDto> checkStockOfConsumedProduct(long warehouseId, Map<Integer, Integer> idsQuantity) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));
        List<Inventory> inventories = warehouse.getInventories();

        List<PostProductDto> consumedProducts = new ArrayList<>();

        for ( Map.Entry<Integer , Integer> map : idsQuantity.entrySet()) {
            consumedProducts.add(getCheckedConsumedProd(inventories , map.getKey() , map.getValue()));
        }
        return consumedProducts;
    }


    @Override
    public PostProductDtoQuantity consumeProduct(long warehouseId, long productId, long consumeQuan) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));
        List<Inventory> inventories = warehouse.getInventories();
        return getConsumedProduct(warehouse , inventories , productId , consumeQuan);
    }


    @Override
    public List<ProductDtoData> getProductsOfWarehouse(long warehouseId) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));

        return getProductIds(warehouse)
                .stream()
                .map(id -> {
                    ProductDtoData data = (ProductDtoData) productData.fetchProduct(ProductDtoEnum.GET_ALL,id);
                    return data;
                })
                .toList();
    }

    @Override
    public List<ProductDtoData> getSearchedProductsOfWarehouse(long warehouseId, String text) {
        Warehouse warehouse = mapper.toWarehouse(getWarehouse(warehouseId));
        List<ProductDtoData> products = productData.fetchSearchedProducts(text);
        List<Long> productsIds = getProductIds(warehouse);
        return products.stream()
                .filter(p->Collections.binarySearch(productsIds , p.getId()) > -1)
                .toList();
    }


    private StockHistory makeStockHistory(Inventory inventory , long quantity){
        StockHistory history = new StockHistory();
        history.setDate(new Date());
        history.setInventory(inventory);
        history.setStockEnum(StockEnum.STOCK);
        history.setQuantity(quantity);
        history.setProductName(inventory.getProduct().getName());
        history.setWarehouseName(inventory.getWarehouse().getName());
        history.setTime(new Date());
        return history;
    }


    private List<Inventory> getInventoryOfProduct(List<Inventory> inventories , long productId){
        return  inventories
                .stream()
                .filter(inventory1 -> inventory1.getProduct().getId() == productId)
                .toList();
    }


    private List<Long> getProductIds(Warehouse warehouse){
        return warehouse.getInventories()
                .stream()
                .map(inventory -> inventory.getProduct().getId())
                .toList();
    }

    private PostProductDtoQuantity getConsumedProduct(Warehouse warehouse , List<Inventory> inventories , long productId, long consumeQuan){

        List<Inventory> warehouseInventoryOfProduct =  getInventoryOfProduct(inventories , productId);

        if (warehouseInventoryOfProduct.isEmpty()){
            throw new NoSuchEntityException(PRODUCT_NOT_FOUND);
        }

        Inventory inventory = warehouseInventoryOfProduct.get(0);

        if(inventory.getProductQuantity() == 0){
            throw new ConsumeProductException(PRODUCT_OUT_STOCK);
        }
        else if(inventory.getProductQuantity() < consumeQuan){
            throw new ConsumeProductException(QUANTITY_GREATER_THAN_STOCK + " " + "Quantity = " + inventory.getProductQuantity());
        }

        Product product = inventory.getProduct();

        inventory.setProductQuantity(inventory.getProductQuantity() - consumeQuan);
        StockHistory history = makeStockHistory(inventory , consumeQuan);
        history.setStockEnum(StockEnum.CONSUME);
        history.setInventory(inventory);
        inventory.addHistory(history);
        Warehouse s = repo.save(warehouse);

        PostProductDtoQuantity productDtoQuantity = new PostProductDtoQuantity();
        productDtoQuantity.setId(product.getId());
        productDtoQuantity.setName(product.getName());
        productDtoQuantity.setQuantity(consumeQuan);

        return productDtoQuantity;
    }


    private PostProductDto getCheckedConsumedProd(List<Inventory> inventories ,long productId, long quantity){
        List<Inventory> warehouseInventoryOfProduct =  getInventoryOfProduct(inventories , productId);

        if (warehouseInventoryOfProduct.isEmpty()){
            throw new NoSuchEntityException(PRODUCT_NOT_FOUND);
        }

        Inventory inventory = warehouseInventoryOfProduct.get(0);
        PostProductDto product= (PostProductDto) productData.fetchProduct(ProductDtoEnum.POST , productId);
        if(inventory.getProductQuantity() == 0 || inventory.getProductQuantity() < quantity){
            product.setInStock(false);
        }else {
            product.setInStock(true);
        }
        product.setQuantity(quantity);
        return product;
    }

}