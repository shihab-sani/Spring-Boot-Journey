package spring.secondproject.DTOS;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductDtos {
    private Long id;
    private String name;
    private BigDecimal price;
}
