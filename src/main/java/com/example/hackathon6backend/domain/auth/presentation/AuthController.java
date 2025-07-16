package com.example.hackathon6backend.domain.auth.presentation;

import com.example.hackathon6backend.domain.auth.presentation.dto.request.LoginRequest;
import com.example.hackathon6backend.domain.auth.presentation.dto.response.TokenResponse;
import com.example.hackathon6backend.domain.auth.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return loginService.execute(loginRequest);
    }


}
