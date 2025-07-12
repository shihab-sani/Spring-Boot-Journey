package testproject.store.testing.repositories;

import org.springframework.data.repository.CrudRepository;
import testproject.store.testing.entities.Products;

public interface ProductsRepository extends CrudRepository<Products, Long> {
}