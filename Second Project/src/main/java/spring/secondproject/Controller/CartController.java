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
import spring.secondproject.Exceptions.CartNotFoundExceptions;
import spring.secondproject.Exceptions.ProductNotFoundExceptions;
import spring.secondproject.Services.CartServices;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    private final CartServices cartServices;

    @PostMapping
    public ResponseEntity<CartDtos> createCart(UriComponentsBuilder uriBuilder) {
        var cartDto = cartServices.createCart();
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemsDtos> addToCart(@PathVariable UUID cartId, @RequestBody AddItemsToCart request) {
        var cartItemsDto = cartServices.addToCart(cartId, request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
    }

    @GetMapping("/{cartId}")
    public CartDtos getCart(@PathVariable UUID cartId) {
        return cartServices.getCart(cartId);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public CartItemsDtos updateItems(@PathVariable UUID cartId,
                                         @PathVariable Long productId,
                                         @Valid @RequestBody UpdateCartRequest request) {
        return cartServices.updateCart(cartId, request.getQuantity(), productId);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteItem(@PathVariable UUID cartId,
                                         @PathVariable Long productId) {
        cartServices.deleteItems(cartId, productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> deleteCart(@PathVariable UUID cartId) {
        cartServices.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundExceptions.class)
    public ResponseEntity<Map<String, String>> handlerCartNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Error", "Cart not found!"));
    }

    @ExceptionHandler(ProductNotFoundExceptions.class)
    public ResponseEntity<Map<String, String>> handlerProductNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", "Product not found!"));
    }
}
