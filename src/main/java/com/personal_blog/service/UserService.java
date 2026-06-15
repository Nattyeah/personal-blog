package com.personal_blog.service;

import com.personal_blog.mapper.UserMapper;
import com.personal_blog.model.UserDto;
import com.personal_blog.model.entity.User;
import com.personal_blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserService {

    private UserRepository repository;
    private UserMapper mapper;

    private UserDto findByEmail(User user) {
        return mapper.toDto(repository.findByEmail(user.getEmail()));
    }

    public UserDto createNewUser(User user) {
        if (isUserRegistered(user)) {
            log.error("User with email {} already exists", user.getEmail());
            return null;
        }
        return mapper.toDto(repository.save(user));
    }

    private boolean isUserRegistered(User user) {
        return Objects.nonNull(findByEmail(user));
    }
}
