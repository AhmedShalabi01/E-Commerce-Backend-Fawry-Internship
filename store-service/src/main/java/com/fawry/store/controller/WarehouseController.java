package com.fawry.store.controller;

import com.fawry.store.dtos.PostProductDto;
import com.fawry.store.dtos.WarehouseDto;
import com.fawry.store.entites.Product;
import com.fawry.store.externalapi.FetchProductData;
import com.fawry.store.services.WarehouseService;
import com.fawry.store.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/store/warehouses")
public class WarehouseController {
    
    @Autowired
    WarehouseService service;

    @Autowired
    FetchProductData data;


    @GetMapping
    ResponseEntity<?> getAllWarehouses(){
        return new ResponseEntity<>(service.getAllWarehouses() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getWarehouse(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getWarehouse(id) , HttpStatus.OK);
    }


    @PostMapping
    ResponseEntity<?> addNewWarehouse(@RequestBody WarehouseDto dto){
        return new ResponseEntity<>(service.createWarehouse(dto) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateWarehouse(@PathVariable long id , @RequestBody WarehouseDto dto){
        return new ResponseEntity<>(service.updateWarehouse(id , dto) , HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteWarehouse(@PathVariable long id){
        service.removeWarehouse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/locations")
    ResponseEntity<?> getAllLocations(){
        return new ResponseEntity<>(service.getAllLocationsNames() , HttpStatus.OK);
    }


    @PostMapping("/{warId}/product/{prodId}/stock/{quantity}")
    ResponseEntity<?> stockQuantityOfProduct(@PathVariable("warId") long warId , @PathVariable("prodId") long prodId , @PathVariable("quantity") long q){
        return new ResponseEntity<>(service.stockProduct(warId , prodId , q) , HttpStatus.OK);
    }


    @PostMapping("/{warId}/product/{prodId}/consume/{quantity}")
    ResponseEntity<?> consumeQuantityOfProduct(@PathVariable("warId") long warId , @PathVariable("prodId") long prodId , @PathVariable("quantity") long q){
        return new ResponseEntity<>(service.consumeProduct(warId , prodId , q) , HttpStatus.ACCEPTED);
    }
    @PostMapping("/{warId}/check/product")
    ResponseEntity<List<PostProductDto>> checkStockOfConsumedProduct(@PathVariable("warId") long warId , @RequestBody Map<Integer , Integer> idsQuan){
        return new ResponseEntity<>(service.checkStockOfConsumedProduct(warId , idsQuan) , HttpStatus.ACCEPTED);
    }
    @PostMapping("/{warId}/products/consume")
    Boolean consumeQuantityOfProducts(@PathVariable("warId") long warId , @RequestBody Map<Integer , Integer> idsQuan){
        return service.consumeProducts(warId , idsQuan) != null;
    }

    @GetMapping("/{id}/inventories")
    ResponseEntity<?> getAllInventoriesOfWarehouse(@PathVariable long id){
        return new ResponseEntity<>(service.getAllInventoriesOfWarehouse(id) , HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    ResponseEntity<?> getProductsOfWarehouse(@PathVariable("id") long warehouseId){
        return new ResponseEntity<>(service.getProductsOfWarehouse(warehouseId) , HttpStatus.OK);
    }

    @GetMapping("/{warId}/product/{productId}")
    ResponseEntity<?> getQuantityOfProduct(@PathVariable("warId") long warId , @PathVariable long productId){
        return new ResponseEntity<>(service.getProductQuantity(warId , productId) , HttpStatus.OK);
    }

    @GetMapping("/{warId}/search/product")
    ResponseEntity<?> getSearchedProducts(@PathVariable long warId , @RequestParam String text){
        return new ResponseEntity<>(service.getSearchedProductsOfWarehouse(warId , text) , HttpStatus.OK);
    }
}
