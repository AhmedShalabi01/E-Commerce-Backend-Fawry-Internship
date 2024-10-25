package com.fawryfinalproject.productapi.repositorytest;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.entity.ProductEntity;
import com.fawryfinalproject.productapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    protected ProductEntity product1,product2;
    @Mock
    private CategoryEntity category;

    @BeforeEach
    void setup(){
        product1 = new ProductEntity(1L,"IPhone 15 ",90000,"link1",category);
        product2 = new ProductEntity(2L,"IPhone 15",1000,"link2",category);

        productRepository.save(product1);
        productRepository.save(product2);
    }


    @Test
    public void testProductRepository_GetAllProducts_ReturnMoreThanOneProduct(){

        List<ProductEntity> productEntities = productRepository.findAll();

        assertThat(productEntities.size()).isEqualTo(2);
    }

    @Test
    public void testProductRepository_DeleteById_ReturnNothing(){

        productRepository.deleteById(product1.getId());

        List<ProductEntity> productEntities = productRepository.findAll();

        assertThat(productEntities).isNotNull();
        assertThat(productEntities.size()).isEqualTo(1);
    }

    @Test
    public void testProductRepository_UpdateProduct_ReturnNothing(){

        product1.setName("IPhone 17");
        product1.setPrice(70000);

        productRepository.save(product1);

        ProductEntity productEntityUpdated = productRepository.findById(product1.getId()).orElseThrow();

        assertThat(productEntityUpdated).isNotNull();
        assertThat(productEntityUpdated.getName()).isEqualTo("IPhone 17");
        assertThat(productEntityUpdated.getPrice()).isEqualTo(70000);
    }
}
