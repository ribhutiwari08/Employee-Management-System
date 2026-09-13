package com.library.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="members")
public class Member {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String name;
 @Email @Column(unique=true,nullable=false) private String email;
 @NotBlank private String password;
 @Enumerated(EnumType.STRING) private Role role=Role.MEMBER;
 public Member() {}
 public Member(String name,String email,String password,Role role){this.name=name;this.email=email;this.password=password;this.role=role;}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;}
}
