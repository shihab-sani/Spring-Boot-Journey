package spring.ecommerceapplication.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
public class ProductDtos {
    private Long id;
    private String name;
    private Double price;
    private Long categoryId;
}
