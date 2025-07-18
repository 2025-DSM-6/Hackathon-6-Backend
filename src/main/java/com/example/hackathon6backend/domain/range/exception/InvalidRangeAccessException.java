package com.example.hackathon6backend.domain.range.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class InvalidRangeAccessException extends HackathonException {
    public static final HackathonException EXCEPTION = new InvalidRangeAccessException();

    private InvalidRangeAccessException() {
        super(ErrorCode.INVALID_RANGE_ACCESS);
    }
}
