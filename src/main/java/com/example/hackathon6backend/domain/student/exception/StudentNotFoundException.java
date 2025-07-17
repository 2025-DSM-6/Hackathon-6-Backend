package com.example.hackathon6backend.domain.student.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class StudentNotFoundException extends HackathonException {
    public static final HackathonException EXCEPTION = new StudentNotFoundException();

    private StudentNotFoundException() {
        super(ErrorCode.STUDENT_NOT_FOUND);
    }
}
