package spring.ecommerceapplication.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.ecommerceapplication.Dtos.RegisterUserDtos;
import spring.ecommerceapplication.Dtos.UpdateUserDtos;
import spring.ecommerceapplication.Dtos.UserDtos;
import spring.ecommerceapplication.Entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDtos toDto(User user);

    User toEntity(RegisterUserDtos request);
    void updateUser(@MappingTarget User user, UpdateUserDtos request);
}
