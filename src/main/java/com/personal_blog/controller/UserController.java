package com.personal_blog.controller;

import com.personal_blog.model.UserDto;
import com.personal_blog.model.entity.User;
import com.personal_blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createNewUser(user));
    }

}
