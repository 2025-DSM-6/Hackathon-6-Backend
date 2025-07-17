package com.example.hackathon6backend.domain.user.controller;

import com.example.hackathon6backend.domain.student.presentation.response.GetMyInformResponse;
import com.example.hackathon6backend.domain.student.presentation.response.GetStudentResponse;
import com.example.hackathon6backend.domain.user.dto.request.UpdateSubjectRequest;
import com.example.hackathon6backend.domain.user.service.GetUserService;
import com.example.hackathon6backend.domain.user.service.UpdateSubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GetUserService getUserService;
    private final UpdateSubjectService updateSubjectService;

    @GetMapping
    public GetMyInformResponse getUser() {
        return getUserService.execute();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSubject(@RequestBody @Valid UpdateSubjectRequest request) {
        updateSubjectService.execute(request);
    }
}
