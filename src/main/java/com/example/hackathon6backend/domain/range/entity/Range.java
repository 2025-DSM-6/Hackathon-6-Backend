package com.example.hackathon6backend.domain.range.entity;

import com.example.hackathon6backend.domain.subject.entity.Subject;
import com.example.hackathon6backend.domain.range.dto.request.RangeContentRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_range")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Range {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_range_id")
    private Long examRangeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    
    @Column(columnDefinition = "TEXT")
    private String memo;

    @OneToMany(mappedBy = "examRange", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RangeContent> rangeContents = new ArrayList<>();

    @Builder
    public Range(Subject subject, String memo) {
        this.subject = subject;
        this.memo = memo;
    }

    public void addRangeContent(RangeContent rangeContent) {
        this.rangeContents.add(rangeContent);
        rangeContent.setExamRange(this);
    }

    public void updateRange(String memo, List<RangeContentRequest> examContents) {
        this.memo = memo;
        this.rangeContents.clear();
        
        examContents.forEach(content -> {
            RangeContent rangeContent = RangeContent.builder()
                    .examName(content.getExamName())
                    .examContent(content.getExamContent())
                    .build();
            addRangeContent(rangeContent);
        });
    }
} 