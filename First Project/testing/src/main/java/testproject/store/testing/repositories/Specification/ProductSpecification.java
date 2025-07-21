package testproject.store.testing.repositories.Specification;

import org.springframework.data.jpa.domain.Specification;
import testproject.store.testing.entities.Products;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Products> byName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Products> byPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Products> byPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
}
