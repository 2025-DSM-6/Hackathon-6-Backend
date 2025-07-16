package com.example.hackathon6backend.domain.range.controller;

import com.example.hackathon6backend.domain.range.dto.response.StudentRangeResponse;
import com.example.hackathon6backend.domain.range.service.StudentRangeService;
import com.example.hackathon6backend.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student/range")
@RequiredArgsConstructor
public class StudentRangeController {

    private final StudentRangeService studentRangeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentRangeResponse>>> getMyRanges(
            @AuthenticationPrincipal Long userId
    ) {
        List<StudentRangeResponse> response = studentRangeService.getMyRanges(userId);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다.",
                        response
                ));
    }
} 