package testproject.store.testing.Services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import testproject.store.testing.entities.Addresses;
import testproject.store.testing.entities.User;
import testproject.store.testing.repositories.AddressesRepository;
import testproject.store.testing.repositories.ProfileRepository;
import testproject.store.testing.repositories.UserRepository;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressesRepository addressesRepository;
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

    @Transactional
    public void showRelatedEntities() {
//        var profile = profileRepository.findById(1L).orElseThrow();
//        System.out.println(profile.getBio());
        var address = addressesRepository.findById(1L).orElseThrow();
        System.out.println(address.getStreet());
    }

    public void persistEntity() {
        var user = User.builder().name("Sani")
                .email("mdsani633@gmail.com")
                .password("shihab14032001").build();

        var address = Addresses.builder().street("123 Main St").city("Anytown").zip(12345).build();

        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteEntity() {
//        userRepository.deleteById(1L);
        var user = userRepository.findById(6L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }
}
