package spring.secondproject.DTOS;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CartDtos {
    private UUID id;
    private List<CartItemsDtos> items = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
