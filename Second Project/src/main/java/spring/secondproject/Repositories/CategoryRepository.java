package spring.secondproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.secondproject.Entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

}