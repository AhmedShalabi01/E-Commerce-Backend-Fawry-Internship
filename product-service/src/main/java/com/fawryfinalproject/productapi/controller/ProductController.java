package com.fawryfinalproject.productapi.controller;

import com.fawryfinalproject.productapi.dto.ProductModel;
import com.fawryfinalproject.productapi.dto.ProductStoreModel;
import com.fawryfinalproject.productapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping("/list")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping("/findAllForStore")
    public ResponseEntity<List<ProductStoreModel>> getAllProductsForStore(){
        return new ResponseEntity<>(productService.getAllProductsForStore(), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping("/findByIdForStore/{id}")
    public ResponseEntity<ProductStoreModel> getProductForStoreById(@PathVariable("id") Long productId){
        return new ResponseEntity<>(productService.getProductForStoreById(productId), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping("/findByIdsForStore")
    public ResponseEntity<List<ProductStoreModel>> getAllProductsForStoreByIds(@RequestBody List<Long> productIds){
        return new ResponseEntity<>(productService.getProductsForStoreByIds(productIds), HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

        @RequestMapping(value = "/search")
        public ResponseEntity<List<ProductModel>> getProductByIdOrNameOrCategory(@RequestParam(name = "Keyword") String Keyword){
            return new ResponseEntity<>(productService.getProductByIdOrNameOrCategory(Keyword), HttpStatus.OK);
        }

    //-------------------------------------------------------------------------------------------//

    @PostMapping(path = "/saveNewProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody ProductModel productModel){
        productService.createNewProduct(productModel);
        return new ResponseEntity<>("Entity Created",HttpStatus.CREATED);
    }

    //-------------------------------------------------------------------------------------------//

    @PutMapping(path = "/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductModel productModel,@PathVariable Long id){
        productService.updateProduct(productModel,id);
        return new ResponseEntity<>("Entity Updated",HttpStatus.OK);
    }

    //-------------------------------------------------------------------------------------------//

    @DeleteMapping (value = "/deleteProduct/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Entity Deleted",HttpStatus.OK);
    }


}
