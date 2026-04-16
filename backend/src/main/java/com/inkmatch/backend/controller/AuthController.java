package com.inkmatch.backend.controller;

import com.inkmatch.backend.dto.request.LoginRequest;
import com.inkmatch.backend.dto.request.RegisterRequest;
import com.inkmatch.backend.dto.response.AuthResponse;
import com.inkmatch.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return new AuthResponse(authService.register(request));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return new AuthResponse(authService.login(request));
    }
}