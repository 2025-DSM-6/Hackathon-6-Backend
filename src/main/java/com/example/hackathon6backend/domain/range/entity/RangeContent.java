package com.example.hackathon6backend.domain.range.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_range_content")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RangeContent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rangeContentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_range_id")
    private Range examRange;
    
    @Column(name = "exam_content", length = 500)
    private String examContent;

    @Builder
    public RangeContent(Range examRange, String examContent) {
        this.examRange = examRange;
        this.examContent = examContent;
    }
} 