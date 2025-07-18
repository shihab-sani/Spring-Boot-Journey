package testproject.store.testing.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import testproject.store.testing.DataTransferObjects.UserSummary;
import testproject.store.testing.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tags","addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"addresses"})
    @Query("select u from User u")
    List<User> findAllWithAddress();

    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :value order by u.email")
    List<UserSummary> findLoyalUser(@Param("value") Integer value);
}
