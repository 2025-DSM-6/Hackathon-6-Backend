package com.example.hackathon6backend.domain.range.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRangeRequest {
    private String memo;
    private List<RangeContentRequest> examRange;
} 