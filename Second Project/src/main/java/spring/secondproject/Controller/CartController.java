package spring.secondproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.secondproject.DTOS.AddItemsToCart;
import spring.secondproject.DTOS.CartDtos;
import spring.secondproject.DTOS.CartItemsDtos;
import spring.secondproject.Entities.Cart;
import spring.secondproject.Entities.CartItem;
import spring.secondproject.Mappers.CartMapper;
import spring.secondproject.Repositories.CartRepository;
import spring.secondproject.Repositories.ProductRepository;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDtos> createCart(UriComponentsBuilder uriBuilder) {
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toDtos(cart);
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
//        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemsDtos> addToCart(@PathVariable UUID cartId, @RequestBody AddItemsToCart request) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItems = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId() == product.getId())
                .findFirst()
                .orElse(null);

        if (cartItems != null) {
            cartItems.setQuantity(cartItems.getQuantity() + 1);
        } else {
            cartItems = new CartItem();
            cartItems.setProduct(product);
            cartItems.setQuantity(1);
            cartItems.setCart(cart);
            cart.getCartItems().add(cartItems);
        }
        cartRepository.save(cart);
        var cartItemsDto = cartMapper.toDtos(cartItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
    }
}
