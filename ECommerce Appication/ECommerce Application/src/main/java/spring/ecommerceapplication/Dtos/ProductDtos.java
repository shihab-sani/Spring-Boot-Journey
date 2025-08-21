package spring.ecommerceapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDtos {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
}
