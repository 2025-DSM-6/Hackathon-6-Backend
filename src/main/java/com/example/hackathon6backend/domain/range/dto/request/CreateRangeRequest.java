package com.example.hackathon6backend.domain.range.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateRangeRequest {
    
    private String memo;
    
    @NotNull(message = "시험범위를 입력해주세요.")
    private List<String> examRange;
} 