package com.example.hackathon6backend.domain.range.dto.response;

import com.example.hackathon6backend.domain.range.entity.Range;
import com.example.hackathon6backend.domain.range.entity.RangeContent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RangeResponse {
    private String subjectName;
    private String memo;
    private List<RangeContentResponse> range;

    public static RangeResponse of(Range range) {
        List<RangeContentResponse> contents = range.getRangeContents().stream()
                .map(content -> RangeContentResponse.builder()
                        .examName(content.getExamContent())
                        .examContent(content.getExamContent())
                        .build())
                .toList();

        return RangeResponse.builder()
                .subjectName(range.getSubject().getSubjectName())
                .memo(range.getMemo())
                .range(contents)
                .build();
    }
} 