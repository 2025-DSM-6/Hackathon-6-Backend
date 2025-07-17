package com.example.hackathon6backend.domain.subject.dto.request;

import com.example.hackathon6backend.domain.room.entity.ClassMajor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateSubjectByMajorRequest {
    @NotBlank(message = "과목명을 입력해주세요.")
    private String subjectName;

    @NotEmpty(message = "전공을 선택해주세요.")
    private List<ClassMajor> classMajors;

    private Integer grade; // null이면 전체 학년
} 