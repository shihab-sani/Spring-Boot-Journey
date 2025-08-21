package spring.ecommerceapplication.Repositories;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.ecommerceapplication.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(@NotBlank(message = "Email is required") @Email(message = "Email must be invalid") String email);
}
