package spring.secondproject.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.secondproject.DTOS.AddItemsToCart;
import spring.secondproject.DTOS.CartDtos;
import spring.secondproject.DTOS.CartItemsDtos;
import spring.secondproject.DTOS.UpdateCartRequest;
import spring.secondproject.Entities.Cart;
import spring.secondproject.Mappers.CartMapper;
import spring.secondproject.Repositories.CartRepository;
import spring.secondproject.Repositories.ProductRepository;

import java.util.Map;
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
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItems = cart.addItem(product);
        cartRepository.save(cart);
        var cartItemsDto = cartMapper.toDtos(cartItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDtos> getCart(@PathVariable UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartMapper.toDtos(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItems(@PathVariable UUID cartId,
                                         @PathVariable Long productId,
                                         @Valid @RequestBody UpdateCartRequest request) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error", "Cart not found!"));
        }

        var cartItems = cart.getCartItem(productId);

        if (cartItems == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error", "Product not found in cart!"));
        }

        cartItems.setQuantity(request.getQuantity());
        cartRepository.save(cart);
        return ResponseEntity.ok(cartMapper.toDtos(cartItems) );
    }
}
