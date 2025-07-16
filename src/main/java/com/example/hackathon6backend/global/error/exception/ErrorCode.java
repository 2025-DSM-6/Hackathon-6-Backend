package com.example.hackathon6backend.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    
    // subject
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "과목을 찾을 수 없습니다."),

    // class
    CLASS_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 반을 찾을 수 없습니다."),
    
    // range
    RANGE_NOT_FOUND(HttpStatus.NOT_FOUND, "시험범위를 찾을 수 없습니다."),
    INVALID_RANGE_ACCESS(HttpStatus.FORBIDDEN, "해당 시험범위에 접근 권한이 없습니다."),
    EMPTY_RANGE_CONTENT(HttpStatus.BAD_REQUEST, "시험범위 내용이 비어있습니다."),
    DUPLICATE_RANGE_EXISTS(HttpStatus.CONFLICT, "이미 등록된 시험범위가 있습니다."),

    // jwt
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "Expired JWT"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "Invalid JWT"),

    // feign
    FEIGN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Feign Bad Request"),
    FEIGN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Feign Unauthorized"),
    FEIGN_FORBIDDEN(HttpStatus.FORBIDDEN, "Feign Forbidden"),

    // user
    ALREADY_USER_EXISTS(HttpStatus.CONFLICT, "Already User Exists"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User Not Found"),
    INVALID_ROLE(HttpStatus.UNAUTHORIZED, "Invalid Role"),

    // auth
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "Password Mismatch");

    private final HttpStatus status;
    private final String message;
}
