package com.inkmatch.backend.seed;

import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.enums.Role;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User.builder()
                    .fullName("System Admin")
                    .email("admin@inkmatch.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .build();

            User customer = User.builder()
                    .fullName("Test Customer")
                    .email("customer@inkmatch.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(Role.CUSTOMER)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .build();

            User artist = User.builder()
                    .fullName("Test Artist")
                    .email("artist@inkmatch.com")
                    .password(passwordEncoder.encode("123456"))
                    .role(Role.ARTIST)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .build();

            userRepository.save(admin);
            userRepository.save(customer);
            userRepository.save(artist);

            System.out.println("✅ Seed data inserted successfully!");
        }
    }
}