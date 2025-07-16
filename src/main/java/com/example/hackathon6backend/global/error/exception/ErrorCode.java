package com.example.hackathon6backend.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    // jwt
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "Expired JWT"),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "Invalid JWT"),

    // feign
    FEIGN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "Feign Bad Request"),
    FEIGN_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Feign Unauthorized"),
    FEIGN_FORBIDDEN(HttpStatus.FORBIDDEN, "Feign Forbidden");

    private final HttpStatus status;
    private final String message;
}
