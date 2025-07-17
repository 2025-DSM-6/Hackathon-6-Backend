package com.example.hackathon6backend.domain.range.dto.response;

import com.example.hackathon6backend.domain.range.entity.RangeContent;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherRangeResponse {
    @JsonProperty("subject_name")
    private String subjectName;
    private String title;
    private String discription;

    public static TeacherRangeResponse of(Subject subject, RangeContent content) {
        return TeacherRangeResponse.builder()
                .subjectName(subject.getSubjectName())
                .title(content.getExamName())
                .discription(content.getExamContent())
                .build();
    }
} 