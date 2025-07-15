package com.example.hackathon6backend.domain.subject.entity;

import com.example.hackathon6backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;
    
    @Column(name = "subject_name", length = 500)
    private String subjectName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Subject(String subjectName, User user) {
        this.subjectName = subjectName;
        this.user = user;
    }
} 