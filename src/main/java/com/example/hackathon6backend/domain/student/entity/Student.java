package com.example.hackathon6backend.domain.student.entity;

import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import com.example.hackathon6backend.domain.subject.entity.ElectiveSubject;
import com.example.hackathon6backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {
    
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "solved_score")
    private Long solvedScore = 0L;
    
    @Column(name = "grade")
    private Integer grade;

    @Column(name = "class_num")
    private Integer classNum;

    @Column(name = "num")
    private Integer num;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "elective_subject")
    private ElectiveSubject electiveSubject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassRoom classRoomEntity;

    @Version
    private Long version = 0L;

    @Builder
    public Student(User user, Long solvedScore, Integer grade, Integer classNum, Integer num,
                  ElectiveSubject electiveSubject, ClassRoom classEntity) {
        this.user = user;
        this.id = user.getId();
        this.solvedScore = solvedScore;
        this.grade = grade;
        this.classNum = classNum;
        this.num = num;
        this.electiveSubject = electiveSubject;
        this.classRoomEntity = classEntity;
        this.version = 0L;
    }

    public void updateSubject(ElectiveSubject electiveSubject) {
        this.electiveSubject = electiveSubject;
    }
} 