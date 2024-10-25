package com.fawryfinalproject.productapi.service;

import com.fawryfinalproject.productapi.mapper.ProductMapper;
import com.fawryfinalproject.productapi.dto.ProductModel;
import com.fawryfinalproject.productapi.dto.ProductStoreModel;
import com.fawryfinalproject.productapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CategoryService categoryService;

    //-------------------------------------------------------------------------------------------//

    public List<ProductModel> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toModel)
                .collect(Collectors.toList());
    }

    //-------------------------------------------------------------------------------------------//
    public List<ProductStoreModel> getAllProductsForStore(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toStoreModel)
                .collect(Collectors.toList());
    }

    //-------------------------------------------------------------------------------------------//
    public ProductStoreModel getProductForStoreById(Long productId){
        return productMapper
                .toStoreModel(productRepository.findById(productId)
                .orElseThrow( ()-> new EntityNotFoundException("The Product with ID : (" + productId + ") does not exist")));
    }


    //-------------------------------------------------------------------------------------------//
    public List<ProductStoreModel> getProductsForStoreByIds(List<Long> productIds){
        return productRepository.findAllById(productIds)
                .stream()
                .map(productMapper::toStoreModel)
                .collect(Collectors.toList());
    }

    //-------------------------------------------------------------------------------------------//

    public List<ProductModel> getProductByIdOrNameOrCategory(String item){

        List<ProductModel> products = productRepository
                .findByCategoryNameContaining(item)
                .stream()
                .map(productMapper::toModel)
                .collect(Collectors.toList());

        products.addAll(productRepository
                .findByIdContaining(item)
                .stream()
                .map(productMapper::toModel).distinct()
                .toList());

        products.addAll(productRepository
                .findByNameContaining(item)
                .stream()
                .map(productMapper::toModel).distinct()
                .toList());

        products = products.stream().distinct().toList();

        if(products.isEmpty()){
            throw new EntityNotFoundException("No products were found matching the search criteria.");
        }

        return products;
    }

    //-------------------------------------------------------------------------------------------//

    public void createNewProduct(@Valid ProductModel productModel){
        int categories = categoryService.getAllCategories().size();
        if (productModel.getCategoryId() > categories){
            throw new IllegalStateException("The Category with ID : " + productModel.getCategoryId() + " does not exist");
        }
        productRepository.save(productMapper.toEntity(productModel));
    }

    //-------------------------------------------------------------------------------------------//

    public void updateProduct(@Valid ProductModel productModel,Long productId ){
        int categories = categoryService.getAllCategories().size();
        if (productModel.getCategoryId() > categories){
            throw new IllegalStateException("The Category with ID : " + productModel.getCategoryId() + " does not exist");
        }
        productMapper.toModel(productRepository
                .findById(productId)
                .orElseThrow( ()-> new EntityNotFoundException("The Product with ID : (" + productId + ") does not exist")));

        productRepository.save(productMapper.toEntity(productModel));
    }

    //-------------------------------------------------------------------------------------------//
    public void deleteProduct(Long productId){
        productMapper.toModel(productRepository
                .findById(productId)
                .orElseThrow( ()-> new EntityNotFoundException("The Product with ID : (" + productId + ") does not exist")));

        productRepository.deleteById(productId);
    }

}
