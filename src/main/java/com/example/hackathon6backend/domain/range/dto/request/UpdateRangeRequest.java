package com.example.hackathon6backend.domain.range.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRangeRequest {
    private String memo;
    private List<RangeContentRequest> examRange;
} 