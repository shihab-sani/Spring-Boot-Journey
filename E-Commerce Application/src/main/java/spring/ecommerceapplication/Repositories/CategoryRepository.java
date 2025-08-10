package spring.ecommerceapplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerceapplication.Entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

}