package spring.secondproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.secondproject.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}