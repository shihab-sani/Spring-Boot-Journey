package testproject.store.testing.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import testproject.store.testing.entities.Products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Products> findProductsByCriteria(String name, BigDecimal min, BigDecimal max) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Products> criteriaQuery = criteriaBuilder.createQuery(Products.class);
        Root<Products> root = criteriaQuery.from(Products.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // like %name%
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (min != null) {
            // price >= min
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min));
        }
        if (max != null) {
            // price <= max
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), max));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
