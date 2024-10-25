package com.fawry.store.service;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.entites.Product;
import com.fawry.store.exceptions.NoSuchEntityException;
import com.fawry.store.externalapi.FetchProductData;
import com.fawry.store.repos.ProductRepo;
import com.fawry.store.services.ProductService;
import com.fawry.store.services.mapper.ProductMapper;
import com.fawry.store.services.serviceimp.ProductServiceImp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrdouctServiceTest {

    @Mock
    ProductRepo repo;

    @Mock
    ProductMapper mapper;

    @Mock
    FetchProductData data;

    @InjectMocks
    ProductService service = new ProductServiceImp();

    final String PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";

    @Test
    void testGetAllStockedProducts(){
        // arrange
        ProductDto ProductDto = new ProductDto();
        ProductDto.setId(1);
        ProductDto.setName("name");
        ProductDto.setPrice(50.5);
        ProductDto.setCategoryName("category");
        Product Product = new Product(1 , "name" , 50.5 , "category");

        when(repo.findAll()).thenReturn(List.of(Product));
        when(mapper.toProductDto(Product)).thenReturn(ProductDto);
        // act
        List<ProductDto> Products =  service.getAllStockedProducts();
        // assert
        Assertions.assertThat(Products).isNotNull().hasSize(1);
    }

    @Test
    void testGetProductById(){
        // arrange
        ProductDto ProductDto = new ProductDto();
        ProductDto.setId(1);
        ProductDto.setName("name");
        ProductDto.setPrice(50.5);
        ProductDto.setCategoryName("category");

        Product Product = new Product(1 , "name" , 50.5 , "category");

        when(repo.findById((long)1)).thenReturn(Optional.of(Product));
        when(mapper.toProductDto(Product)).thenReturn(ProductDto);
        // act
        ProductDto dto = service.getProduct((long)1);
        // assert
        assertThat(dto).isNotNull().isEqualTo(ProductDto);
    }

    @Test
    void testGetProductByIdAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.getProduct((long)1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(PRODUCT_NOT_FOUND);
    }


    @Test
    void testCreateNewProduct(){
        ProductDto ProductDto = new ProductDto();
        ProductDto.setId(1);
        ProductDto.setName("name");
        ProductDto.setPrice(50.5);
        ProductDto.setCategoryName("category");
        Product Product = new Product(1 , "name" , 50.5 , "category");

        when(repo.save(Product)).thenReturn(Product);
        when(mapper.toProductDto(Product)).thenReturn(ProductDto);
        when(mapper.toProduct(ProductDto)).thenReturn(Product);
        // act
        ProductDto dto = service.addNewProduct(ProductDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(ProductDto);
    }

    @Test
    void testUpdateProductAndProductNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.updateProduct(1L , any(ProductDto.class)))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(PRODUCT_NOT_FOUND);
    }

    @Test
    void testUpdateProduct(){
        // arrange
        ProductDto ProductDto = new ProductDto();
        ProductDto.setId(1);
        ProductDto.setName("name");
        ProductDto.setPrice(50.5);
        ProductDto.setCategoryName("category");
        Product Product = new Product(1 , "name" , 50.5 , "category");

        when(repo.findById((long)1)).thenReturn(Optional.ofNullable(Product));
        when(mapper.toProductDto(Product)).thenReturn(ProductDto);
        when(repo.save(Product)).thenReturn(Product);

        // act
        ProductDto dto = service.updateProduct(1L , ProductDto);
        // assert
        assertThat(dto).isNotNull().isEqualTo(ProductDto);
    }

    @Test
    void testDeleteProductAndNotFound(){
        // arrange
        when(repo.findById((long)1)).thenReturn(Optional.empty());
        // act
        // assert
        assertThatThrownBy(() -> service.removeProduct(1))
                .isInstanceOf(NoSuchEntityException.class)
                .hasMessage(PRODUCT_NOT_FOUND);
    }

    @Test
    void testDeleteInventory(){
        // arrange;
        Product Product = new Product(1 , "name" , 50.5 , "category");
        when(repo.findById((long)1)).thenReturn(Optional.of(Product));
        // act
        org.junit.jupiter.api.Assertions.assertAll(() -> service.removeProduct(1));
    }


}
