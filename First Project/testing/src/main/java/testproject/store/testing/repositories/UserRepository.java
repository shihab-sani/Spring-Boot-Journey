package testproject.store.testing.repositories;

import org.springframework.data.repository.CrudRepository;
import testproject.store.testing.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
