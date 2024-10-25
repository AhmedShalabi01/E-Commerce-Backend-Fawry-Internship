package com.fawryfinalproject.productapi.mapper;

import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.dto.CategoryModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryModel toModel(final CategoryEntity categoryEntity);
    CategoryEntity toEntity(final CategoryModel categoryModel);

}
