package com.fawry.store.services.serviceimp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fawry.store.dtos.ProductDto;
import com.fawry.store.dtos.ProductDtoData;
import com.fawry.store.entites.Product;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.externalapi.FetchProductData;
import com.fawry.store.repos.ProductRepo;
import com.fawry.store.services.ProductService;
import com.fawry.store.services.mapper.ProductMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Primary
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepo repo;

    @Autowired
    ProductMapper mapper;

    @Autowired
    FetchProductData productData;

    final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";


    @Override
    public List<ProductDto> getAllStockedProducts() {
        return repo.findAll()
                .stream()
                .map(mapper::toProductDto)
                .toList();
    }

    @SneakyThrows
    @Override
    public ProductDto getProduct(Long id) {
        return mapper.toProductDto(repo.findById(id)
                .orElseThrow(()-> new NoSuchEntityException(PRODUCT_NOT_FOUND)));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = repo.findById(id).orElseThrow(()-> new NoSuchEntityException(PRODUCT_NOT_FOUND));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return mapper.toProductDto(repo.save(product));
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        return mapper.toProductDto(repo.save(mapper.toProduct(productDto)));
    }

    @Override
    public void removeProduct(long id) {
        Product product = repo.findById(id).orElseThrow(()-> new NoSuchEntityException(PRODUCT_NOT_FOUND));
        repo.delete(product);
    }

    @Override
    public List<ProductDtoData> getAllFetchedProducts() {
        return productData.fetchAllProducts();
    }

    @Override
    public List<ProductDtoData> getSearchedProducts(String text) {
        return productData.fetchSearchedProducts(text);
    }
}
