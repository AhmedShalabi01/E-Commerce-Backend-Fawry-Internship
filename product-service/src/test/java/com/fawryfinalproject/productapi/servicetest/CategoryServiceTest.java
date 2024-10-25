package com.fawryfinalproject.productapi.servicetest;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.mapper.CategoryMapper;
import com.fawryfinalproject.productapi.dto.CategoryModel;
import com.fawryfinalproject.productapi.repository.CategoryRepository;
import com.fawryfinalproject.productapi.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    private  List<CategoryEntity> sampleCategories;

    private CategoryEntity categoryEntity1, categoryEntity2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository,categoryMapper);

        sampleCategories = new ArrayList<>();
        categoryEntity1 = new CategoryEntity("Test1");
        categoryEntity2 = new CategoryEntity("Test2");

        sampleCategories.add(categoryEntity1);
        sampleCategories.add(categoryEntity2);
    }

    @Test
    public void testGetAllCategories() {

        List<CategoryModel> sampleCategoryModels = new ArrayList<>();

        CategoryModel categoryModel1 = new CategoryModel("Test1");
        CategoryModel categoryModel2 = new CategoryModel("Test2");

        sampleCategoryModels.add(categoryModel1);
        sampleCategoryModels.add(categoryModel2);

        when(categoryRepository.findAll()).thenReturn(sampleCategories);

        when(categoryMapper.toModel(any(CategoryEntity.class)))
                .thenReturn(categoryModel1)
                .thenReturn(categoryModel2);

        List<CategoryModel> result = categoryService.getAllCategories();

        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    public void testCreateNewCategory() {

        CategoryModel categoryModel = new CategoryModel("Test1");

        when(categoryMapper.toEntity(categoryModel)).thenReturn(categoryEntity1);

        categoryService.createNewCategory(categoryModel);

        verify(categoryRepository, times(1)).save(any(CategoryEntity.class));
    }

    @Test
    public void testDeleteCategory() {

        String categoryName = "Test1";

        CategoryModel categoryModel = new CategoryModel("Test1");

        when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(categoryEntity1));
        when(categoryMapper.toModel(any(CategoryEntity.class))).thenReturn(categoryModel);
        when(categoryMapper.toEntity(any(CategoryModel.class))).thenReturn(categoryEntity1);

        categoryService.deleteCategory(categoryName);

        verify(categoryRepository, times(1)).deleteById(categoryModel.getCategoryId());
    }

}