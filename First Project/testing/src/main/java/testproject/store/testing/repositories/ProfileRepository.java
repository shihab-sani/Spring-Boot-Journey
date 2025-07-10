package testproject.store.testing.repositories;

import org.springframework.data.repository.CrudRepository;
import testproject.store.testing.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}