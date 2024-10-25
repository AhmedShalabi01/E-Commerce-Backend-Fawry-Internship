package com.fawryfinalproject.productapi.service;

import com.fawryfinalproject.productapi.mapper.CategoryMapper;
import com.fawryfinalproject.productapi.dto.CategoryModel;
import com.fawryfinalproject.productapi.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    //-------------------------------------------------------------------------------------------//

    public List<CategoryModel> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toModel)
                .collect(Collectors.toList());
    }

    //-------------------------------------------------------------------------------------------//

    public void createNewCategory(@Valid CategoryModel categoryModel){
        categoryRepository.save(categoryMapper.toEntity(categoryModel));
    }

    //-------------------------------------------------------------------------------------------//

    public void deleteCategory(String categoryName){
        CategoryModel categoryModel = categoryMapper.toModel(categoryRepository
                .findByName(categoryName)
                .orElseThrow( ()-> new IllegalStateException("The Category with ID : " + categoryName + " does not exist")));

        categoryRepository.deleteById(categoryMapper.toEntity(categoryModel).getCategoryId());
    }


}
