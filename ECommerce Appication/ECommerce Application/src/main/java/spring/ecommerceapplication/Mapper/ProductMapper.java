package spring.ecommerceapplication.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.ecommerceapplication.Dtos.ProductDtos;
import spring.ecommerceapplication.Dtos.UpdateProductDtos;
import spring.ecommerceapplication.Entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDtos toDto(Product product);

    Product toEntity(ProductDtos request);
    void updateProduct(@MappingTarget Product product, UpdateProductDtos request);
}
