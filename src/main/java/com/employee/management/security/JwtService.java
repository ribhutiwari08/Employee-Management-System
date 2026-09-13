package com.employee.management.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key = Keys.hmacShaKeyFor("employee-management-system-secret-key-2026-very-secure".getBytes(StandardCharsets.UTF_8));
    public String generateToken(String username) {
        return Jwts.builder().subject(username).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key).compact();
    }
    public String extractUsername(String token) { return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject(); }
    public boolean isValid(String token) {
        try { return extractUsername(token) != null; } catch (Exception e) { return false; }
    }
}
