package com.example.hackathon6backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher {
    
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Version
    private Long version;

    @Builder
    public Teacher(User user) {
        this.user = user;
        this.id = user.getUserId();
    }
} 