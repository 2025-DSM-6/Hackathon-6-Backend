package com.example.hackathon6backend.global.feign.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class FeignBadRequestException extends HackathonException {
    public static final HackathonException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException() {
        super(ErrorCode.FEIGN_BAD_REQUEST);
    }
}
