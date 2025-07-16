package com.example.hackathon6backend.domain.range.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RangeContentRequest {
    private String examName;
    private String examContent;
} 