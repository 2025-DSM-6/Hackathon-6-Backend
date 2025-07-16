package com.example.hackathon6backend.domain.range.controller;

import com.example.hackathon6backend.domain.range.dto.request.CreateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.request.UpdateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.response.RangeResponse;
import com.example.hackathon6backend.domain.range.service.RangeService;
import com.example.hackathon6backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/range")
@RequiredArgsConstructor
public class RangeController {

    private final RangeService rangeService;

    @PostMapping("/{subject-id}")
    public ResponseEntity<ApiResponse<Void>> createRange(
            @PathVariable("subject-id") Long subjectId,
            @RequestParam Long userId,
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

    @GetMapping
    public ResponseEntity<ApiResponse<List<RangeResponse>>> getAllRanges(
            @RequestParam Long userId
    ) {
        List<RangeResponse> response = rangeService.getAllRanges(userId);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다.",
                        response
                ));
    }

    @DeleteMapping("/{range-id}")
    public ResponseEntity<ApiResponse<Void>> deleteRange(
            @PathVariable("range-id") Long rangeId,
            @RequestParam Long userId
    ) {
        rangeService.deleteRange(rangeId, userId);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }

    @PutMapping("/{range-id}")
    public ResponseEntity<ApiResponse<Void>> updateRange(
            @PathVariable("range-id") Long rangeId,
            @RequestParam Long userId,
            @RequestBody @Valid UpdateRangeRequest request
    ) {
        rangeService.updateRange(rangeId, userId, request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }
} 