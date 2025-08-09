package spring.ecommerceapplication.Controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.ecommerceapplication.DTOS.UpdateUserDtos;
import spring.ecommerceapplication.DTOS.UserDtos;
import spring.ecommerceapplication.DTOS.RegisterUserDtos;
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
    public Iterable<UserDtos> getAllUsers(
            @RequestHeader(required = false, name="auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy, Sort sort) {
        System.out.println(authToken);
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

    @PostMapping
    public ResponseEntity<UserDtos> createUser(@RequestBody RegisterUserDtos request, UriComponentsBuilder uriBuilder) {
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtos> updateUser(@PathVariable(name = "id") Long id, @RequestBody UpdateUserDtos request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.updateUser(user, request);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}
