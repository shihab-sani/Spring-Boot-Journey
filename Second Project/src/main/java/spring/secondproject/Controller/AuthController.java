package spring.secondproject.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import spring.secondproject.DTOS.JwtResponse;
import spring.secondproject.DTOS.LogInRequest;
import spring.secondproject.DTOS.UserDtos;
import spring.secondproject.Mappers.UserMapper;
import spring.secondproject.Repositories.UserRepository;
import spring.secondproject.Services.JwtServices;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtServices jwtServices;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> logIn(@Valid @RequestBody LogInRequest request,
                                             HttpServletResponse response) {
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtServices.generateAccessToken(user);
        var refreshToken = jwtServices.generateRefreshToken(user);

        var cookies = new Cookie("refreshToken", refreshToken);
        cookies.setHttpOnly(true);
        cookies.setPath("/auth/refresh");
        cookies.setMaxAge(604800); //7 days
        cookies.setSecure(true);
        response.addCookie(cookies);

        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @PostMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String authHeader) {
        var token = authHeader.replace("Bearer ", "");
        return jwtServices.validateJwtToken(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDtos> me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        var user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        var userDtos = userMapper.toDto(user);
        return ResponseEntity.ok(userDtos);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handlerBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
