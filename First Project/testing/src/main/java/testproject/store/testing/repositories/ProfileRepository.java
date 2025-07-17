package testproject.store.testing.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import testproject.store.testing.entities.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query(value = "SELECT p.id, p.bio, p.phoneNumber, p.dateOfBirth, p.loyaltyPoints FROM Profile p WHERE p.loyaltyPoints > :value")
    long findByLoyaltyPoints(@Param("value") int value);
}