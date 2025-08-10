package spring.ecommerceapplication.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.ecommerceapplication.DTOS.ProductDtos;
import spring.ecommerceapplication.DTOS.UpdateProductDtos;
import spring.ecommerceapplication.Entities.Products;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDtos toDto(Products product);

    Products toEntity(ProductDtos request);
    void updateProduct(@MappingTarget Products product, UpdateProductDtos request);
}
