package com.example.hackathon6backend.domain.user.controller;

import com.example.hackathon6backend.domain.user.dto.request.UpdateSubjectRequest;
import com.example.hackathon6backend.domain.user.dto.response.GetUserResponse;
import com.example.hackathon6backend.domain.user.service.GetUserService;
import com.example.hackathon6backend.domain.user.service.UpdateSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final GetUserService getUserService;
    private final UpdateSubjectService updateSubjectService;

    @PostMapping
    public GetUserResponse getUser() {
        return getUserService.execute();
    }

    @PatchMapping
    public void updateSubject(@RequestBody UpdateSubjectRequest request) {
        updateSubjectService.execute(request);
    }
}
