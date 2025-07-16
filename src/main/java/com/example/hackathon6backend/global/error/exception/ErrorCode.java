package com.example.hackathon6backend.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    // 공통 예외
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    
    // Subject 관련 예외
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "과목을 찾을 수 없습니다."),
    
    // Range 관련 예외
    RANGE_NOT_FOUND(HttpStatus.NOT_FOUND, "시험범위를 찾을 수 없습니다."),
    INVALID_RANGE_ACCESS(HttpStatus.FORBIDDEN, "해당 시험범위에 접근 권한이 없습니다."),
    EMPTY_RANGE_CONTENT(HttpStatus.BAD_REQUEST, "시험범위 내용이 비어있습니다."),
    DUPLICATE_RANGE_EXISTS(HttpStatus.CONFLICT, "이미 등록된 시험범위가 있습니다."),
    CLASS_NOT_FOUND(HttpStatus.NOT_FOUND, "반 정보를 찾을 수 없습니다."),

    // jwt
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "Expired JWT"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "Invalid JWT");

    private final HttpStatus status;
    private final String message;
}
