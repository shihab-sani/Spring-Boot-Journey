package spring.secondproject.DTOS;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemsDtos {
    private CartProductDtos product;
    private int quantity;
    private BigDecimal totalPrice;
}
