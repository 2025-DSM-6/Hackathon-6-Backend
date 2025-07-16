package com.example.hackathon6backend.domain.subject.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSubjectRequest {
    private String subjectName;
    private List<Long> classIds;  // 과목을 등록할 반 ID 목록
} 