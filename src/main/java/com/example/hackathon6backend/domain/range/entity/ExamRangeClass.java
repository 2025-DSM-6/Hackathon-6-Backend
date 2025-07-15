package com.example.hackathon6backend.domain.range.entity;

import com.example.hackathon6backend.domain.subject.entity.Class;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_range_class")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExamRangeClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examRangeSubjectId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classEntity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Builder
    public ExamRangeClass(Class classEntity, Subject subject) {
        this.classEntity = classEntity;
        this.subject = subject;
    }
} 