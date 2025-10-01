package spring.secondproject.Repositories;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.secondproject.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(@NotBlank(message = "Email is required") @Email(message = "Email must be invalid") String email);

    Optional<User> findByEmail(String email);
}
