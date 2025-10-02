package spring.secondproject.Services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.secondproject.Repositories.UserRepository;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UserServices implements UserDetailsService {
    private final UserRepository userRespository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRespository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("User with email "+email+" not found"));
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
