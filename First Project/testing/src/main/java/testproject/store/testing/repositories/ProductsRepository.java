package testproject.store.testing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import testproject.store.testing.DataTransferObjects.ProductSummary;
import testproject.store.testing.entities.Categories;
import testproject.store.testing.entities.Products;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    //Sort(By Order)
    List<Products> findByNameOrderByPrice(String name);

    //Limit(Top/First)
    List<Products> findTop3ByNameOrderByPrice(String name);
    List<Products> findFirst3ByNameLikeOrderByPrice(String name);

//    List<Products> findByPriceBetweenOrderByName(BigDecimal min, BigDecimal max);

    //With SQL commands
//    @Query(value = "SELECT * FROM products p WHERE p.price BETWEEN :min AND :max ORDER BY p.name", nativeQuery = true)
//    List<Products> findByPrice(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    //With JPQL
    @Procedure("findProductsByPrice")
    List<Products> findProducts(BigDecimal min, BigDecimal max);

    @Query(value = "SELECT COUNT(*) FROM Products p WHERE p.price BETWEEN :min AND :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query(value = "UPDATE Products p SET p.price = :newPrice WHERE p.category.id = :catalogId")
    void updatePrice(BigDecimal newPrice, Byte catalogId);

    @Query("select p.id, p.name from Products p where p.category = :category")
    List<ProductSummary> findCategory(@Param("category") Categories category);
}