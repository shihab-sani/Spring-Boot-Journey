package spring.secondproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.secondproject.Entities.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository  extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(UUID cartId);
}
