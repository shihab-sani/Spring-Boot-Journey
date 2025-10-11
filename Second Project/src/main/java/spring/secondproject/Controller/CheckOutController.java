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
import spring.secondproject.Entities.OrderItem;
import spring.secondproject.Entities.OrderStatus;
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

        var order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(authServices.getCurrentUser());

        cart.getCartItems().forEach(item -> {
            var orderItems = new OrderItem();
            orderItems.setOrder(order);
            orderItems.setQuantity(item.getQuantity());
            orderItems.setProduct(item.getProduct());
            orderItems.setTotalPrice(item.getPrice());
            orderItems.setUnitPrice(item.getProduct().getPrice());
            order.getOrderItems().add(orderItems);
        });
        orderRepository.save(order);

       return ResponseEntity.ok(new CheckOutResponse(order.getId()));
    }
}
