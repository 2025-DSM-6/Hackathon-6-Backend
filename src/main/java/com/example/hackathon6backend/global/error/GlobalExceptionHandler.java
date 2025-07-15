package com.example.hackathon6backend.global.error;

import com.example.hackathon6backend.global.error.exception.ErrorCode;
import com.example.hackathon6backend.global.error.exception.HackathonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HackathonException.class)
    public ResponseEntity<ErrorResponse> hackathonExceptionHandler(HackathonException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build(),
            HttpStatus.valueOf(errorCode.getStatus().value())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build(),
            HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value())
        );
    }

}
