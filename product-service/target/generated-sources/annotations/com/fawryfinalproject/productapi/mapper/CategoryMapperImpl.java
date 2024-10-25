package com.fawryfinalproject.productapi.mapper;

import com.fawryfinalproject.productapi.dto.CategoryModel;
import com.fawryfinalproject.productapi.entity.CategoryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-30T23:39:44+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryModel toModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setCategoryId( categoryEntity.getCategoryId() );
        categoryModel.setName( categoryEntity.getName() );

        return categoryModel;
    }

    @Override
    public CategoryEntity toEntity(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId( categoryModel.getCategoryId() );
        categoryEntity.setName( categoryModel.getName() );

        return categoryEntity;
    }
}
