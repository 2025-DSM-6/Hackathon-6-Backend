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
    @Column(name = "range_content_id")
    private Long rangeContentId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_range_id")
    private Range examRange;
    
    @Column(name = "exam_name", length = 500)
    private String examName;
    
    @Column(name = "exam_content", length = 500)
    private String examContent;

    @Builder
    public RangeContent(Range examRange, String examName, String examContent) {
        this.examRange = examRange;
        this.examName = examName;
        this.examContent = examContent;
    }

    public void setExamRange(Range examRange) {
        this.examRange = examRange;
    }
} 