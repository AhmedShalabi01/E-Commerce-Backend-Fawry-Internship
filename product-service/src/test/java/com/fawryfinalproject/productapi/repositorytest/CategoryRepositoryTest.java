package com.fawryfinalproject.productapi.repositorytest;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    protected CategoryEntity category1,category2;

    @BeforeEach
    public void setup(){
        category1 = new CategoryEntity("Mobile");
        category2 = new CategoryEntity("Headphone");

        categoryRepository.save(category1);
        categoryRepository.save(category2);
    }

    @Test
    public void testCategoryRepository_GetAllCategories_ReturnMoreThanOneCategory(){

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        assertThat(categoryEntities.size()).isEqualTo(2);
    }

    @Test
    public void testCategoryRepository_DeleteById_ReturnNothing(){

        CategoryEntity categoryEntity = categoryRepository
                .findByName(category1.getName())
                .orElseThrow();

        categoryRepository.deleteById(categoryEntity.getCategoryId());

        List<CategoryEntity> categoryEntities = categoryRepository.findAll();

        assertThat(categoryEntities).isNotNull();
        assertThat(categoryEntities.size()).isEqualTo(1);
    }
}
