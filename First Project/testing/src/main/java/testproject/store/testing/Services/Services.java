package testproject.store.testing.Services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import testproject.store.testing.entities.User;
import testproject.store.testing.repositories.UserRepository;

@AllArgsConstructor
@Service
public class Services {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void EntityState() {
        var user = User.builder().name("Shihab")
                .email("2320590@iub.edu.bd")
                .password("Shihab123").build();

        if (entityManager.contains(user)){
            System.out.println("persistence");
        } else {
            System.out.println("transient or detached");
        }

        userRepository.save(user);

        if (entityManager.contains(user)){
            System.out.println("persistence");
        } else {
            System.out.println("transient or detached");
        }
    }
}
