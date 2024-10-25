package com.fawryfinalproject.productapi.mapper;

import com.fawryfinalproject.productapi.dto.ProductModel;
import com.fawryfinalproject.productapi.dto.ProductStoreModel;
import com.fawryfinalproject.productapi.entity.CategoryEntity;
import com.fawryfinalproject.productapi.entity.ProductEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-30T23:39:43+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel toModel(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setCategoryName( productEntityCategoryName( productEntity ) );
        productModel.setCategoryId( productEntityCategoryCategoryId( productEntity ) );
        productModel.setId( productEntity.getId() );
        productModel.setName( productEntity.getName() );
        productModel.setPrice( productEntity.getPrice() );
        productModel.setImageUrl( productEntity.getImageUrl() );
        productModel.setCategory( productEntity.getCategory() );

        return productModel;
    }

    @Override
    public ProductEntity toEntity(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setCategory( productModelToCategoryEntity( productModel ) );
        productEntity.setId( productModel.getId() );
        productEntity.setName( productModel.getName() );
        productEntity.setPrice( productModel.getPrice() );
        productEntity.setImageUrl( productModel.getImageUrl() );

        return productEntity;
    }

    @Override
    public ProductStoreModel toStoreModel(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductStoreModel productStoreModel = new ProductStoreModel();

        productStoreModel.setCategoryName( productEntityCategoryName( productEntity ) );
        productStoreModel.setId( productEntity.getId() );
        productStoreModel.setName( productEntity.getName() );
        productStoreModel.setPrice( productEntity.getPrice() );
        productStoreModel.setImageUrl( productEntity.getImageUrl() );

        return productStoreModel;
    }

    private String productEntityCategoryName(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }
        CategoryEntity category = productEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private int productEntityCategoryCategoryId(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return 0;
        }
        CategoryEntity category = productEntity.getCategory();
        if ( category == null ) {
            return 0;
        }
        int categoryId = category.getCategoryId();
        return categoryId;
    }

    protected CategoryEntity productModelToCategoryEntity(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( productModel.getCategoryName() );
        categoryEntity.setCategoryId( productModel.getCategoryId() );

        return categoryEntity;
    }
}
