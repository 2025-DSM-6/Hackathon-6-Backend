package com.example.hackathon6backend.global.xquare;

import com.example.hackathon6backend.domain.auth.presentation.dto.request.LoginRequest;
import com.example.hackathon6backend.domain.user.dto.response.UserResponse;
import com.example.hackathon6backend.global.feign.CustomErrorDecoder;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "xquare-client", url = "https://prod-server.xquare.app/dsm-login/user", configuration = CustomErrorDecoder.class)
public interface XquareClient {
    @PostMapping("/user-data")
    UserResponse user(@RequestBody @Valid LoginRequest request);
}
