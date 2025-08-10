package spring.ecommerceapplication.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.ecommerceapplication.DTOS.ProductDtos;
import spring.ecommerceapplication.Entities.Products;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDtos toDto(Products product);

    Products toEntity(ProductDtos request);
}
