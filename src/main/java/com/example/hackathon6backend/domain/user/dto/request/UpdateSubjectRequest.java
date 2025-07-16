package com.example.hackathon6backend.domain.user.dto.request;

import com.example.hackathon6backend.domain.subject.entity.ElectiveSubject;
import jakarta.validation.constraints.NotNull;

public record UpdateSubjectRequest(
    @NotNull
    ElectiveSubject electiveSubject
) {
}
