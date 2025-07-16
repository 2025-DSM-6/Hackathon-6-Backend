package com.example.hackathon6backend.domain.range.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class DuplicateRangeExistsException extends HackathonException {
    public static final HackathonException EXCEPTION = new DuplicateRangeExistsException();

    private DuplicateRangeExistsException() {
        super(ErrorCode.DUPLICATE_RANGE_EXISTS);
    }
}
