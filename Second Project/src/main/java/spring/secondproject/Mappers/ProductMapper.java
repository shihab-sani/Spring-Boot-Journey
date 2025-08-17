package spring.secondproject.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.secondproject.DTOS.ProductDtos;
import spring.secondproject.DTOS.UpdateProductDtos;
import spring.secondproject.Entities.Products;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDtos toDto(Products product);

    Products toEntity(ProductDtos request);
    void updateProduct(@MappingTarget Products product, UpdateProductDtos request);
}
