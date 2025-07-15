package com.example.hackathon6backend.domain.range.entity;

import com.example.hackathon6backend.domain.subject.entity.Subject;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "range")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Range {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examRangeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    
    @Column(columnDefinition = "TEXT")
    private String memo;

    @Builder
    public Range(Subject subject, String memo) {
        this.subject = subject;
        this.memo = memo;
    }
} 