package com.example.hackathon6backend.domain.user.entity.repository;

import com.example.hackathon6backend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountId(String accountId);
}
