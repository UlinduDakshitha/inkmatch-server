package com.inkmatch.backend.service;

import com.inkmatch.backend.dto.request.LoginRequest;
import com.inkmatch.backend.dto.request.RegisterRequest;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.exception.BadRequestException;
import com.inkmatch.backend.repository.UserRepository;
import com.inkmatch.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        validateRegisterRequest(request);

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(LoginRequest request) {

        validateLoginRequest(request);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new BadRequestException("Request body is required");
        }

        if (isBlank(request.getFullName())) {
            throw new BadRequestException("Full name is required");
        }

        if (isBlank(request.getEmail())) {
            throw new BadRequestException("Email is required");
        }

        if (isBlank(request.getPassword())) {
            throw new BadRequestException("Password is required");
        }

        if (request.getRole() == null) {
            throw new BadRequestException("Role is required");
        }
    }

    private void validateLoginRequest(LoginRequest request) {
        if (request == null) {
            throw new BadRequestException("Request body is required");
        }

        if (isBlank(request.getEmail())) {
            throw new BadRequestException("Email is required");
        }

        if (isBlank(request.getPassword())) {
            throw new BadRequestException("Password is required");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}

