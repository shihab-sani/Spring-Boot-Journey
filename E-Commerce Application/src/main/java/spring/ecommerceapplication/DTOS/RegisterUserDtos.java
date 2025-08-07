package spring.ecommerceapplication.DTOS;

import lombok.Data;

@Data
public class RegisterUserDtos {
    private String name;
    private String email;
    private String password;
}
