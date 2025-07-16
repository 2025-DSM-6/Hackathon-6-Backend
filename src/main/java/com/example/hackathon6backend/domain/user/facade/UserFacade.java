package com.example.hackathon6backend.domain.user.facade;

import com.example.hackathon6backend.domain.user.entity.User;
import com.example.hackathon6backend.domain.user.entity.repository.UserRepository;
import com.example.hackathon6backend.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByAccountId(accountId)
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
