package spring.secondproject.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.secondproject.DTOS.UpdateUserDtos;
import spring.secondproject.DTOS.UserDtos;
import spring.secondproject.DTOS.RegisterUserDtos;
import spring.secondproject.Entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDtos toDto(User user);

    User toEntity(RegisterUserDtos request);
    void updateUser(@MappingTarget User user, UpdateUserDtos request);
}
