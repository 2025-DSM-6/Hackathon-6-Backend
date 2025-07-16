package com.example.hackathon6backend.domain.user.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class InvalidRoleException extends HackathonException {
    public static final HackathonException EXCEPTION = new InvalidRoleException();

    private InvalidRoleException() {
        super(ErrorCode.INVALID_ROLE);
    }
}
