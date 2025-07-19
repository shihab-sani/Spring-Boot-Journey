package testproject.store.testing.repositories;

import testproject.store.testing.entities.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Products> findProductsByCriteria(String name, BigDecimal min, BigDecimal max);
}
