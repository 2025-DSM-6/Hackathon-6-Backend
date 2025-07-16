package com.example.hackathon6backend.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

    @NotBlank
    String accountId,

    @NotBlank
    String password
) {
}
