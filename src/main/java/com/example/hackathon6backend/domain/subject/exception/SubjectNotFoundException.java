package com.example.hackathon6backend.domain.subject.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class SubjectNotFoundException extends HackathonException {
    public static final HackathonException EXCEPTION = new SubjectNotFoundException();

    private SubjectNotFoundException() {
        super(ErrorCode.SUBJECT_NOT_FOUND);
    }
}
