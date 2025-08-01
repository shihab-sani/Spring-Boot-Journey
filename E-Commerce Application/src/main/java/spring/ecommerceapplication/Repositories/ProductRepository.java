package spring.ecommerceapplication.Repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.ecommerceapplication.Entities.Products;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Products> findAllByCategory_Id(byte category_id);

    @EntityGraph(attributePaths = {"category"})
    @Query("SELECT p FROM Products p")
    List<Products> findAllByCategory();
}
