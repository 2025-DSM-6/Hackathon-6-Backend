package com.example.hackathon6backend.domain.user.dto.response;

import com.example.hackathon6backend.domain.user.entity.Role;
import lombok.Builder;

@Builder
public record UserResponse(
    String accountId,
    String password,
    String name,
    Integer grade,
    Integer classNum,
    Integer num,
    Role userRole
) {
}
