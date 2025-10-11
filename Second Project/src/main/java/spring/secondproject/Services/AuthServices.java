package spring.secondproject.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.secondproject.Entities.User;
import spring.secondproject.Repositories.UserRepository;

@AllArgsConstructor
@Service
public class AuthServices {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }
}
