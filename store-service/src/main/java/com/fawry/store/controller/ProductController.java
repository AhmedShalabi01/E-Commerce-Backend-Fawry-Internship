package com.fawry.store.controller;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    ResponseEntity<?> getAllProductsFromExternalApi(){
        return new ResponseEntity<>(service.getAllFetchedProducts() , HttpStatus.OK);
    }

    @GetMapping("/stock")
    ResponseEntity<?> getAllStockedProducts(){
        return new ResponseEntity<>(service.getAllStockedProducts() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getProduct(@PathVariable("id") long id){
        return new ResponseEntity<>(service.getProduct(id) , HttpStatus.OK);
    }


    @PostMapping
    ResponseEntity<?> addNewProduct(@RequestBody ProductDto dto){
        return new ResponseEntity<>(service.addNewProduct(dto) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable long id , @RequestBody ProductDto dto){
        return new ResponseEntity<>(service.updateProduct(id , dto) , HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable long id){
        service.removeProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    ResponseEntity<?> getSearchedProducts(@RequestParam String text){
        return new ResponseEntity<>(service.getSearchedProducts(text) , HttpStatus.OK);
    }
}

