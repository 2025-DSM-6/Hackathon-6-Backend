package com.example.hackathon6backend.domain.user.dto.response;

import com.example.hackathon6backend.domain.subject.entity.ElectiveSubject;
import lombok.Builder;

@Builder
public record GetUserResponse(
    Long id,
    String userName,
    Integer grade,
    Integer classNum,
    Integer num,
    ElectiveSubject electiveSubject
) {
}
