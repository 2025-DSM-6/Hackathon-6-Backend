package com.example.hackathon6backend.domain.subject.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ElectiveSubject {
    NONE("1학년"),
    SERVER_DEV("서버 프로그래밍"),
    FRONT_DEV("프론트웨어 프로그래밍"),
    LINUX_DEV("리눅스 프로그래밍"),
    AI_USE("인공지능 활용");

    private final String name;
} 