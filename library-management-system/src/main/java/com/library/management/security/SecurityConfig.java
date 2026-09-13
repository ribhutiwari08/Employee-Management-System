package com.library.management.security;

import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration public class SecurityConfig{
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean SecurityFilterChain filterChain(HttpSecurity http,JwtFilter filter)throws Exception{
  http.csrf(c->c.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(a->a
   .requestMatchers("/api/auth/**").permitAll().requestMatchers("/api/admin/**").hasRole("ADMIN").requestMatchers("/api/member/**").hasAnyRole("MEMBER","ADMIN").anyRequest().authenticated())
   .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class); return http.build();
 }
}
