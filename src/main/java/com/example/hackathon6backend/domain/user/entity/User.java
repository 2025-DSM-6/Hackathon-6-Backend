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
    private Long userId;
    
    @Column(length = 100)
    private String accountId;
    
    @Column(length = 50)
    private String username;

    @Builder
    public User(String accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }
} 