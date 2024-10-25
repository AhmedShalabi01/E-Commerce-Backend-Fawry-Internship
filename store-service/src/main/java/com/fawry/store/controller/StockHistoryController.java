package com.fawry.store.controller;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.entites.StockHistory;
import com.fawry.store.services.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/histories")
public class StockHistoryController {

    @Autowired
    StockHistoryService service;

    @GetMapping
    ResponseEntity<?> getAllHistories(){
        return new ResponseEntity<>(service.getAllHistories() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getHistory(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getHistory(id) , HttpStatus.OK);
    }

   @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHistory(@PathVariable long id){
        service.removeHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<?> getProductHistory(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getHistoryOfProduct(id) , HttpStatus.OK);
    }

    @GetMapping("/warehouse/{id}")
    ResponseEntity<?> getWarehouseHistory(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getHistoryOfWarehouse(id) , HttpStatus.OK);
    }
}
