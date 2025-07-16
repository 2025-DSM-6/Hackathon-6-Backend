package com.example.hackathon6backend.domain.room.exception;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;

public class ClassRoomNotFoundException extends HackathonException {
    public static final HackathonException EXCEPTION = new ClassRoomNotFoundException();

    private ClassRoomNotFoundException() {
        super(ErrorCode.CLASS_ROOM_NOT_FOUND);
    }
}
