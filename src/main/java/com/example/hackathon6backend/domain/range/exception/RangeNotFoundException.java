package com.example.hackathon6backend.domain.range.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class RangeNotFoundException extends HackathonException {
    public static final HackathonException EXCEPTION = new RangeNotFoundException();

    private RangeNotFoundException() {
        super(ErrorCode.RANGE_NOT_FOUND);
    }
}
