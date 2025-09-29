package spring.secondproject.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.secondproject.DTOS.CartDtos;
import spring.secondproject.DTOS.CartItemsDtos;
import spring.secondproject.Entities.Cart;
import spring.secondproject.Entities.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDtos toDtos(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getPrice())")
    CartItemsDtos toDtos(CartItem cartItem);
}
