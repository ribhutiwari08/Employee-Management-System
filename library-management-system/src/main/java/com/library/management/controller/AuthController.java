package com.library.management.controller;

import com.library.management.model.*; import com.library.management.repository.MemberRepository; import com.library.management.security.JwtService; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.Map;

@RestController @RequestMapping("/api/auth") public class AuthController{
 private final MemberRepository members; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(MemberRepository members,PasswordEncoder encoder,JwtService jwt){this.members=members;this.encoder=encoder;this.jwt=jwt;}
 @PostMapping("/register") public Member register(@RequestBody Member m){m.setPassword(encoder.encode(m.getPassword())); if(m.getRole()==null)m.setRole(Role.MEMBER); return members.save(m);}
 @PostMapping("/login") public Map<String,String> login(@RequestParam String email,@RequestParam String password){Member m=members.findByEmail(email).orElseThrow(()->new RuntimeException("Invalid credentials")); if(!encoder.matches(password,m.getPassword()))throw new RuntimeException("Invalid credentials"); return Map.of("token",jwt.generate(m.getEmail(),m.getRole().name()),"role",m.getRole().name());}
}
