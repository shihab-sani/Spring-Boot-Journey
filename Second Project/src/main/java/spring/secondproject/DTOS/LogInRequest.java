package spring.secondproject.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequest {
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
