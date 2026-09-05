package com.personal_blog.configuration;

import com.personal_blog.model.entity.User;
import com.personal_blog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repository, PasswordEncoder passwordEncoder) {

        return args -> {

            if (repository.findByEmail("admin@email.com") == null) {

                User user = new User();

                user.setUsername("admin");
                user.setEmail("admin@email.com");
                user.setPassword(passwordEncoder.encode("admin"));

                repository.save(user);
            }
        };
    }
}
