package testproject.store.testing;

public interface UserRepository {
    void save(User user);   
    User findByEmail(String email);
}
