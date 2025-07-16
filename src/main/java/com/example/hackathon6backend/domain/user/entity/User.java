package com.example.hackathon6backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String accountId;
    
    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String accountId, String password, String username, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.username = username;
        this.role = role;
    }
} 