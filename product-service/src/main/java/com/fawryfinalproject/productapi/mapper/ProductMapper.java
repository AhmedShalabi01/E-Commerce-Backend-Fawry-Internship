package com.fawryfinalproject.productapi.mapper;

import com.fawryfinalproject.productapi.entity.ProductEntity;
import com.fawryfinalproject.productapi.dto.ProductModel;
import com.fawryfinalproject.productapi.dto.ProductStoreModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductModel toModel(final ProductEntity productEntity);
    @Mapping(source = "categoryName", target = "category.name")
    @Mapping(source = "categoryId", target = "category.categoryId")
    ProductEntity toEntity(final ProductModel productModel);
    @Mapping(source = "category.name", target = "categoryName")
    ProductStoreModel toStoreModel(final ProductEntity productEntity);
}
