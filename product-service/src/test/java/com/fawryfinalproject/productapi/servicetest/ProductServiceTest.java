package com.fawryfinalproject.productapi.servicetest;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.entity.ProductEntity;
import com.fawryfinalproject.productapi.mapper.ProductMapper;
import com.fawryfinalproject.productapi.dto.ProductModel;
import com.fawryfinalproject.productapi.repository.ProductRepository;
import com.fawryfinalproject.productapi.service.CategoryService;
import com.fawryfinalproject.productapi.service.ProductService;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private ProductService productService;

    private final List<ProductEntity> sampleProducts = new ArrayList<>();
    private  ProductEntity productEntity1,productEntity2;

    private ProductModel productModel1,productModel2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productEntity1 =  new ProductEntity(5L,"IPhone 15",5,"link1", new CategoryEntity("Test1"));
        productEntity2 =  new ProductEntity(6L,"IPhone 16",6,"link2",new CategoryEntity("Test2"));
        sampleProducts.add(productEntity1);
        sampleProducts.add(productEntity2);

        productModel1 = new ProductModel(5L,"IPhone 15",5,"link1",new CategoryEntity("Test1"));
        productModel2 = new ProductModel(6L,"IPhone 16",6,"link2",new CategoryEntity("Test1"));
    }

    @Test
    public void testGetAllProducts() {

        when(productRepository.findAll()).thenReturn(sampleProducts);

        when(productMapper.toModel(any(ProductEntity.class))).thenReturn(productModel1).thenReturn(productModel2);

        List<ProductModel> result = productService.getAllProducts();

        assertNotNull(result);
        verify(productMapper, times(2)).toModel(any());
    }

    @Test
    public void testCreateNewProduct() {

        when(productMapper.toEntity(productModel1)).thenReturn(productEntity1);

        productService.createNewProduct(productModel1);

        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateProduct() {

        Long productId = 5L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity1));
        when(productMapper.toEntity(any(ProductModel.class))).thenReturn(productEntity1);

        productService.updateProduct(productModel1, productId);

        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void testDeleteProduct() {

        Long productId = 5L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity1));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

}
