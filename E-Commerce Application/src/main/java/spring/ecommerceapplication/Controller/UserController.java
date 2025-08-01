package spring.ecommerceapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.ecommerceapplication.DTOS.UserDtos;
import spring.ecommerceapplication.Mappers.UserMapper;
import spring.ecommerceapplication.Repositories.UserRepository;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDtos> getAllUsers(@RequestParam(required = false, defaultValue = "", name = "sort") String sortBy, Sort sort) {
        if(!Set.of("name","email").contains(sortBy)) {
            sortBy= "name";
        }

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtos> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
