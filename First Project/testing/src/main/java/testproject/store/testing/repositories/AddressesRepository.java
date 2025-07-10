package testproject.store.testing.repositories;

import org.springframework.data.repository.CrudRepository;
import testproject.store.testing.entities.Addresses;

public interface AddressesRepository extends CrudRepository<Addresses, Long> {
}