package com.example.hackathon6backend.domain.student.presentation.response;

import lombok.Builder;

@Builder
public record GetStudentResponse(
    Long id,
    String userName,
    Integer grade,
    Integer classNum,
    Integer num
) {
}
