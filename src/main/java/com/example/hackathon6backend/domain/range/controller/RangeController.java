package com.example.hackathon6backend.domain.range.controller;

import com.example.hackathon6backend.domain.range.dto.request.CreateRangeRequest;
import com.example.hackathon6backend.domain.range.service.RangeService;
import com.example.hackathon6backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/range")
@RequiredArgsConstructor
public class RangeController {

    private final RangeService rangeService;

    @PostMapping("/{subject-id}")
    public ResponseEntity<ApiResponse<Void>> createRange(
            @PathVariable("subject-id") Long subjectId,
            @AuthenticationPrincipal Long userId,
            @RequestBody @Valid CreateRangeRequest request
    ) {
        rangeService.createRange(subjectId, userId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "정상적으로 처리되었습니다."
                ));
    }
} 