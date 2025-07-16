package com.example.hackathon6backend.domain.subject.entity;

import com.example.hackathon6backend.domain.range.entity.ExamRangeClass;
import com.example.hackathon6backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;
    
    @Column(name = "subject_name", length = 500)
    private String subjectName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamRangeClass> examRangeClasses = new ArrayList<>();

    @Builder
    public Subject(String subjectName, User user) {
        this.subjectName = subjectName;
        this.user = user;
    }

    public void addExamRangeClass(ExamRangeClass examRangeClass) {
        this.examRangeClasses.add(examRangeClass);
        examRangeClass.setSubject(this);
    }

    public void updateSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
} 