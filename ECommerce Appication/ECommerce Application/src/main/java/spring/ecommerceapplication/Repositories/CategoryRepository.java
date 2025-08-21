package spring.ecommerceapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerceapplication.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}