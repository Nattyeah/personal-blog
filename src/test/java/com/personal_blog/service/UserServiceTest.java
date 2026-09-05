package com.personal_blog.service;

import com.personal_blog.model.entity.User;
import com.personal_blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository repository;
    @InjectMocks
    UserService service;

    @Test
    void loadsUser() {
        User user = new User();
        user.setEmail("admin@email.com");
        user.setPassword("encoded");

        when(repository.findByEmail(user.getEmail())).thenReturn(user);
        assertThat(service.loadUserByUsername(user.getEmail()).getPassword()).isEqualTo("encoded");
    }

    @Test
    void rejectsUnknownUser() {
        assertThatThrownBy(() -> service.loadUserByUsername("none@email.com"))
                .isInstanceOf(org.springframework.security.core.userdetails.UsernameNotFoundException.class);
    }
}
