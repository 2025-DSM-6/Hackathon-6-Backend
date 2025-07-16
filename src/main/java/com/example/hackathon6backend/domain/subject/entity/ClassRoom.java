package com.example.hackathon6backend.domain.subject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_class_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassRoom {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "class_major")
    private ClassMajor classMajor;
    
    @Column(name = "class_name", length = 3)
    private String className;
    
    private Integer grade;

    @Builder
    public ClassRoom(ClassMajor classMajor, String className, Integer grade) {
        this.classMajor = classMajor;
        this.className = className;
        this.grade = grade;
    }
} 