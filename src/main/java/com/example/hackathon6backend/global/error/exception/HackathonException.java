package com.example.hackathon6backend.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HackathonException extends RuntimeException{
    private final ErrorCode errorCode;
}
