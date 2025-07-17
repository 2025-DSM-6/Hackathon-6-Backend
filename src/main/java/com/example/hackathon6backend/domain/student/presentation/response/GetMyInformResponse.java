package com.example.hackathon6backend.domain.student.presentation.response;

import com.example.hackathon6backend.domain.subject.entity.ElectiveSubject;
import lombok.Builder;

@Builder
public record GetMyInformResponse(
    Long id,
    String userName,
    Integer grade,
    Integer classNum,
    Integer num,
    ElectiveSubject electiveSubject
) {
}
