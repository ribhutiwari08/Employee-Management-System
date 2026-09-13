package com.employee.management.controller;

import com.employee.management.security.JwtService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    public AuthController(JwtService jwtService) { this.jwtService = jwtService; }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required");
        return Map.of("token", jwtService.generateToken(username), "role", "ADMIN");
    }
}
