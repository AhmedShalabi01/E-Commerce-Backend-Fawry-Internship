package com.fawry.store.services;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.dtos.ProductDtoData;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllStockedProducts();
    ProductDto getProduct(Long id);
    ProductDto updateProduct(Long id , ProductDto productDto);
    ProductDto addNewProduct(ProductDto productDto);
    void removeProduct(long id);

    List<ProductDtoData> getAllFetchedProducts();
    List<ProductDtoData> getSearchedProducts(String text);

}
