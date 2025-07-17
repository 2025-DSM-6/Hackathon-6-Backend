package com.example.hackathon6backend.domain.range.controller;

import com.example.hackathon6backend.domain.range.dto.request.CreateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.request.UpdateRangeRequest;
import com.example.hackathon6backend.domain.range.dto.response.RangeResponse;
import com.example.hackathon6backend.domain.range.dto.response.TeacherRangeResponse;
import com.example.hackathon6backend.domain.range.service.RangeService;
import com.example.hackathon6backend.domain.user.facade.UserFacade;
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
    private final UserFacade userFacade;

    @PostMapping("/{subject-id}")
    public ResponseEntity<ApiResponse<Void>> createRange(
            @PathVariable("subject-id") Long subjectId,
            @RequestBody @Valid CreateRangeRequest request
    ) {
        Long finalUserId = userFacade.getUser().getId();
        rangeService.createRange(subjectId, finalUserId, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "정상적으로 처리되었습니다."
                ));
    }

    @GetMapping("/addition")
    public ResponseEntity<ApiResponse<List<TeacherRangeResponse>>> getAllRanges(
            @RequestParam(required = false) Long userId
    ) {
        Long finalUserId = userId != null ? userId : userFacade.getUser().getId();
        List<TeacherRangeResponse> response = rangeService.getAllRanges(finalUserId);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다.",
                        response
                ));
    }

    @DeleteMapping("/{range-id}")
    public ResponseEntity<ApiResponse<Void>> deleteRange(
            @PathVariable("range-id") Long rangeId
    ) {
        Long finalUserId = userFacade.getUser().getId();
        rangeService.deleteRange(rangeId, finalUserId);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }

    @PutMapping("/{range-id}")
    public ResponseEntity<ApiResponse<Void>> updateRange(
            @PathVariable("range-id") Long rangeId,
            @RequestBody @Valid UpdateRangeRequest request
    ) {
        Long finalUserId = userFacade.getUser().getId();
        rangeService.updateRange(rangeId, finalUserId, request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }
} 