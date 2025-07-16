package com.example.hackathon6backend.domain.range.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class RangeException extends HackathonException {
    public RangeException(ErrorCode errorCode) {
        super(errorCode);
    }
} 