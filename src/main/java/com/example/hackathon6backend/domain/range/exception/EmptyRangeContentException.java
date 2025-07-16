package com.example.hackathon6backend.domain.range.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class EmptyRangeContentException extends HackathonException {
    public static final HackathonException EXCEPTION = new EmptyRangeContentException();

    private EmptyRangeContentException() {
        super(ErrorCode.EMPTY_RANGE_CONTENT);
    }
}
