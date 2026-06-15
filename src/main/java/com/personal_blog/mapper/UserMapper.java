package com.personal_blog.mapper;

import com.personal_blog.model.UserDto;
import com.personal_blog.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
}
