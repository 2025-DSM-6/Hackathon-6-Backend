package com.example.hackathon6backend.domain.user.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class AlreadyUserExistsException extends HackathonException {
    public static final HackathonException EXCEPTION = new AlreadyUserExistsException();

    private AlreadyUserExistsException() {
        super(ErrorCode.ALREADY_USER_EXISTS);
    }
}
