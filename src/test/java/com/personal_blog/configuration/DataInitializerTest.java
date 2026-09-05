package com.personal_blog.configuration;

import com.personal_blog.model.entity.User;
import com.personal_blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DataInitializerTest {

    @Test
    void createsAdminOnlyWhenMissing() throws Exception {
        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);

        when(encoder.encode("admin")).thenReturn("encoded");
        new DataInitializer().init(repository, encoder).run();
        verify(repository).save(argThat(user ->
                user.getUsername().equals("admin") && user.getEmail().equals("admin@email.com")
                        && user.getPassword().equals("encoded")));

        when(repository.findByEmail("admin@email.com")).thenReturn(new User());
        new DataInitializer().init(repository, encoder).run();
        verify(repository, times(1)).save(any());
    }
}
