package com.example.hackathon6backend.domain.range.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RangeContentResponse {
    private String examName;
    private String examContent;
} 