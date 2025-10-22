package spring.secondproject.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.secondproject.DTOS.CheckOutRequest;
import spring.secondproject.DTOS.CheckOutResponse;
import spring.secondproject.Entities.Order;
import spring.secondproject.Repositories.CartRepository;
import spring.secondproject.Repositories.OrderRepository;
import spring.secondproject.Services.AuthServices;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping( "/checkout")
public class CheckOutController {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthServices authServices;

    @PostMapping
    public ResponseEntity<?> checkOut(@Valid @RequestBody CheckOutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);

        if (cart == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Cart not found!"));
        }

        if (cart.getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Cart is empty!"));
        }

        var order = Order.fromCart(cart, authServices.getCurrentUser());
        orderRepository.save(order);

       return ResponseEntity.ok(new CheckOutResponse(order.getId()));
    }
}
