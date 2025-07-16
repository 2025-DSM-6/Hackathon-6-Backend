package com.example.hackathon6backend.domain.room.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassMajor {
    COMMON("공통교육과정"),
    SOFTWARE("소프트웨어개발과"),
    EMBEDDED("임베디드소프트웨어과"),
    AI("인공지능소프트웨어과");

    private final String name;
} 