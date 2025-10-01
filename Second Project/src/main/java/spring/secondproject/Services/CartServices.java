package spring.secondproject.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.secondproject.DTOS.CartDtos;
import spring.secondproject.DTOS.CartItemsDtos;
import spring.secondproject.Entities.Cart;
import spring.secondproject.Exceptions.CartNotFoundExceptions;
import spring.secondproject.Exceptions.ProductNotFoundExceptions;
import spring.secondproject.Mappers.CartMapper;
import spring.secondproject.Repositories.CartRepository;
import spring.secondproject.Repositories.ProductRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartServices {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    public CartDtos createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDtos(cart);
    }

    public CartItemsDtos addToCart(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptions();
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundExceptions();
        }

        var cartItems = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toDtos(cartItems);
    }

    public CartDtos getCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptions();
        }
        return cartMapper.toDtos(cart);
    }

    public CartItemsDtos updateCart(UUID cartId, Integer quantity, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptions();
        }

        var cartItems = cart.getCartItem(productId);
        if (cartItems == null) {
            throw new ProductNotFoundExceptions();
        }

        cartItems.setQuantity(quantity);
        cartRepository.save(cart);
        return cartMapper.toDtos(cartItems);
    }

    public void deleteItems(UUID cartId, Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptions();
        }

        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    public void deleteCart(UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundExceptions();
        }
        cart.clearCart();
        cartRepository.delete(cart);
    }
}
