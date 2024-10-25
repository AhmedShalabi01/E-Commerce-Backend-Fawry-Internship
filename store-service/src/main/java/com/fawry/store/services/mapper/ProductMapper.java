package com.fawry.store.services.mapper;

import com.fawry.store.dtos.ProductDto;
import com.fawry.store.entites.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);
}
