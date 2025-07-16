package com.example.hackathon6backend.global.feign.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class FeignUnauthorizedException extends HackathonException {
    public static final HackathonException EXCEPTION = new FeignUnauthorizedException();

    private FeignUnauthorizedException() {
        super(ErrorCode.FEIGN_UNAUTHORIZED);
    }
}
