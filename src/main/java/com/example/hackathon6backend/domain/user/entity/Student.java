package com.example.hackathon6backend.domain.user.entity;

import com.example.hackathon6backend.domain.classroom.entity.Class;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "solved_score")
    private Long solvedScore;
    
    @Column(name = "school_num", length = 4)
    private String schoolNum;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "elective_subject")
    private ElectiveSubject electiveSubject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classEntity;

    @Builder
    public Student(User user, Long solvedScore, String schoolNum, 
                  ElectiveSubject electiveSubject, Class classEntity) {
        this.user = user;
        this.solvedScore = solvedScore;
        this.schoolNum = schoolNum;
        this.electiveSubject = electiveSubject;
        this.classEntity = classEntity;
    }
} 