package spring.ecommerceapplication.DTOS;

import lombok.Data;

@Data
public class UpdateProductDtos {
    private String name;
    private Double price;
    private Long categoryId;
}
