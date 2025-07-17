package com.example.hackathon6backend.domain.subject.dto.response;

import com.example.hackathon6backend.domain.range.entity.ExamRangeClass;
import com.example.hackathon6backend.domain.subject.entity.Subject;
import com.example.hackathon6backend.domain.room.entity.ClassMajor;
import com.example.hackathon6backend.domain.room.entity.ClassRoom;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class SubjectResponse {
    private Long subjectId;
    private String subjectName;
    private List<Integer> classRange;  // 담당하는 반 번호 목록

    private record GradeMajor(int grade, ClassMajor major) {}

    public static SubjectResponse of(Subject subject) {
        List<Integer> classRange = subject.getExamRangeClasses().stream()
                .map(ExamRangeClass::getClassRoomEntity)
                .map(room -> new GradeMajor(room.getGrade(), room.getClassMajor()))
                .distinct()
                .map(SubjectResponse::mapGradeMajorToCode)
                .filter(Objects::nonNull)
                .sorted()
                .toList();

        return SubjectResponse.builder()
                .subjectId(subject.getSubjectId())
                .subjectName(subject.getSubjectName())
                .classRange(classRange)
                .build();
    }

    private static Integer mapGradeMajorToCode(GradeMajor gradeMajor) {
        if (gradeMajor.grade() == 1) {
            return 1;
        }

        return switch (gradeMajor.grade()) {
            case 2 -> switch (gradeMajor.major()) {
                case SOFTWARE -> 2;
                case EMBEDDED -> 3;
                case AI -> 4;
                default -> null;
            };
            case 3 -> switch (gradeMajor.major()) {
                case SOFTWARE -> 5;
                case EMBEDDED -> 6;
                case AI -> 7;
                default -> null;
            };
            default -> null;
        };
    }
} 