package com.example.hackathon6backend.domain.subject.dto.response;

import com.example.hackathon6backend.domain.subject.entity.Subject;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SubjectResponse {
    private Long subjectId;
    private String subjectName;
    private List<Integer> classRange;  // 담당하는 반 번호 목록

    public static SubjectResponse of(Subject subject) {
        List<Integer> classRange = subject.getExamRangeClasses().stream()
                .map(examRangeClass -> examRangeClass.getClassRoomEntity().getGrade())
                .distinct()
                .sorted()
                .toList();

        return SubjectResponse.builder()
                .subjectId(subject.getSubjectId())
                .subjectName(subject.getSubjectName())
                .classRange(classRange)
                .build();
    }
} 