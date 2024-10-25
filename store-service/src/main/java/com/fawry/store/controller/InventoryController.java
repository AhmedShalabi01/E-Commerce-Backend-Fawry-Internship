package com.fawry.store.controller;

import com.fawry.store.dtos.InventoryDto;
import com.fawry.store.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/inventories")
public class InventoryController {
    
    @Autowired
    InventoryService service;

    @GetMapping
    ResponseEntity<?> getAllInventories(){
        return new ResponseEntity<>(service.getAllInventories() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getInventory(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getInventoryById(id) , HttpStatus.OK);
    }


    @PostMapping
    ResponseEntity<?> createNewInventory(@RequestBody InventoryDto dto){
        return new ResponseEntity<>(service.createInventory(dto) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateInventory(@PathVariable long id , @RequestBody InventoryDto dto){
        return new ResponseEntity<>(service.updateInventory(id , dto) , HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteInventory(@PathVariable long id){
        service.destroyInventory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
