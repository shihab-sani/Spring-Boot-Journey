package spring.ecommerceapplication.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.ecommerceapplication.DTOS.UpdateUserDtos;
import spring.ecommerceapplication.DTOS.UserDtos;
import spring.ecommerceapplication.DTOS.RegisterUserDtos;
import spring.ecommerceapplication.Entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDtos toDto(User user);

    User toEntity(RegisterUserDtos request);
    void updateUser(@MappingTarget User user, UpdateUserDtos request);
}
