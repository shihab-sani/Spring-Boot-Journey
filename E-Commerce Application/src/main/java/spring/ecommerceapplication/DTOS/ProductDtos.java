package spring.ecommerceapplication.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDtos {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
}
