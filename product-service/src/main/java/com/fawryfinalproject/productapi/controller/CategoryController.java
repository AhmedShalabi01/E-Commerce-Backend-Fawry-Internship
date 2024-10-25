package com.fawryfinalproject.productapi.controller;

import com.fawryfinalproject.productapi.dto.CategoryModel;
import com.fawryfinalproject.productapi.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //-------------------------------------------------------------------------------------------//

    @GetMapping("/categories/list")
    public List<CategoryModel> getAllCategories(){
        return categoryService.getAllCategories();
    }

    //-------------------------------------------------------------------------------------------//

    @PostMapping("/categories/saveNewCategory")
    public void createNewCategory(@RequestBody CategoryModel categoryModel){
        categoryService.createNewCategory(categoryModel);
    }

    //-------------------------------------------------------------------------------------------//

    @DeleteMapping (value = "/categories/deleteCategory/{name}")
    public void deleteCategoryByName(@PathVariable String name){
        categoryService.deleteCategory(name);
    }
}
