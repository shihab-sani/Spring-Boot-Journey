package testproject.store.testing.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import testproject.store.testing.DataTransferObjects.UserSummary;
import testproject.store.testing.entities.Profile;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
//    @Query(value = "SELECT p.id, p.bio, p.phoneNumber, p.dateOfBirth, p.loyaltyPoints FROM Profile p WHERE p.loyaltyPoints > :value")
//    List<Profile> findByLoyaltyPoints(@Param("value") Integer value);
//
//    @Query("select p from Profile p where p.loyaltyPoints > :value")
//    @EntityGraph(attributePaths = {"user"})
//    List<Profile> findLoyaltyPoint(@Param("value") Integer value);
//
//    @Query("select p.id as id, p.user.email as email from Profile p where p.loyaltyPoints > :value order by p.user.email")
//    @EntityGraph(attributePaths = {"profile"})
//    List<UserSummary> findLoyaltyPointsByOrder(@Param("value") Integer value);
}