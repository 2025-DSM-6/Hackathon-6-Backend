package com.example.hackathon6backend.domain.range.dto.response;

import com.example.hackathon6backend.domain.range.entity.Range;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StudentRangeResponse {
    private Long subjectId;
    private String subjectName;
    private String memo;
    private List<RangeContentResponse> range;

    public static StudentRangeResponse of(Range range) {
        List<RangeContentResponse> contents = range.getRangeContents().stream()
                .map(content -> RangeContentResponse.builder()
                        .examName(content.getExamName())
                        .examContent(content.getExamContent())
                        .build())
                .toList();

        return StudentRangeResponse.builder()
                .subjectId(range.getSubject().getSubjectId())
                .subjectName(range.getSubject().getSubjectName())
                .memo(range.getMemo())
                .range(contents)
                .build();
    }
} 