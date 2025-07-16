package com.example.hackathon6backend.domain.user.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class UserNotFoundException extends HackathonException {
    public static final HackathonException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
