package com.example.hackathon6backend.domain.auth.presentation.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenResponse(
    String accessToken,
    LocalDateTime accessExp
) {
}
