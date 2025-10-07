package spring.secondproject.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.secondproject.DTOS.ChangePasswordRequest;
import spring.secondproject.DTOS.UpdateUserDtos;
import spring.secondproject.DTOS.UserDtos;
import spring.secondproject.DTOS.RegisterUserDtos;
import spring.secondproject.Entities.Role;
import spring.secondproject.Mappers.UserMapper;
import spring.secondproject.Repositories.UserRepository;

import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterUserDtos request, UriComponentsBuilder uriBuilder) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("email", "Email is already taken!"));
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
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

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody ChangePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
