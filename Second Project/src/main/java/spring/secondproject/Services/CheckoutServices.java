package spring.secondproject.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.secondproject.DTOS.CheckOutRequest;
import spring.secondproject.DTOS.CheckOutResponse;
import spring.secondproject.Entities.Order;
import spring.secondproject.Exceptions.CartEmptyException;
import spring.secondproject.Exceptions.CartNotFoundExceptions;
import spring.secondproject.Repositories.CartRepository;
import spring.secondproject.Repositories.OrderRepository;

import java.util.Map;

@Service
@AllArgsConstructor
public class CheckoutServices {
    private final CartServices cartServices;
    private final OrderRepository orderRepository;
    private final AuthServices authServices;
    private final CartRepository cartRepository;

    public CheckOutResponse checkout(CheckOutRequest request){
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);

        if (cart == null) {
            throw new CartNotFoundExceptions();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authServices.getCurrentUser());
        orderRepository.save(order);

        return new CheckOutResponse(order.getId());
    }
}
