package com.example.hackathon6backend.domain.subject.controller;

import com.example.hackathon6backend.domain.subject.dto.request.CreateSubjectByMajorRequest;
import com.example.hackathon6backend.domain.subject.dto.request.CreateSubjectRequest;
import com.example.hackathon6backend.domain.subject.dto.request.UpdateSubjectRequest;
import com.example.hackathon6backend.domain.subject.dto.response.SubjectResponse;
import com.example.hackathon6backend.domain.subject.service.SubjectService;
import com.example.hackathon6backend.domain.user.facade.UserFacade;
import com.example.hackathon6backend.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createSubject(
            @RequestBody @Valid CreateSubjectRequest request
    ) {
        subjectService.createSubject(userFacade.getUser().getId(), request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "정상적으로 처리되었습니다."
                ));
    }

    @PostMapping("/by-major")
    public ResponseEntity<ApiResponse<Void>> createSubjectByMajor(
            @RequestBody @Valid CreateSubjectByMajorRequest request
    ) {
        subjectService.createSubjectByMajor(userFacade.getUser().getId(), request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        HttpStatus.CREATED,
                        "정상적으로 처리되었습니다."
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectResponse>>> getMySubjects() {
        List<SubjectResponse> response = subjectService.getMySubjects(userFacade.getUser().getId());
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다.",
                        response
                ));
    }

    @PutMapping("/{subject-id}")
    public ResponseEntity<ApiResponse<Void>> updateSubject(
            @PathVariable("subject-id") Long subjectId,
            @RequestBody @Valid UpdateSubjectRequest request
    ) {
        subjectService.updateSubject(subjectId, userFacade.getUser().getId(), request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }

    @DeleteMapping("/{subject-id}")
    public ResponseEntity<ApiResponse<Void>> deleteSubject(
            @PathVariable("subject-id") Long subjectId
    ) {
        subjectService.deleteSubject(subjectId, userFacade.getUser().getId());
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK,
                        "정상적으로 처리되었습니다."
                ));
    }
} 