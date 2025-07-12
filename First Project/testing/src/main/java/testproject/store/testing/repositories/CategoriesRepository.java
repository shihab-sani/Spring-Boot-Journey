package testproject.store.testing.repositories;

import org.springframework.data.repository.CrudRepository;
import testproject.store.testing.entities.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Byte> {
}