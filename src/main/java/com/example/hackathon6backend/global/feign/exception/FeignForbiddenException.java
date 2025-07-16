package com.example.hackathon6backend.global.feign.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class FeignForbiddenException extends HackathonException {
    public static final HackathonException EXCEPTION = new FeignForbiddenException();

    private FeignForbiddenException() {
        super(ErrorCode.FEIGN_FORBIDDEN);
    }
}
