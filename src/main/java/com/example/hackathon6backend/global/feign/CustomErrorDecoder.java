package com.example.hackathon6backend.global.feign;

import com.example.hackathon6backend.global.feign.exception.FeignBadRequestException;
import com.example.hackathon6backend.global.feign.exception.FeignForbiddenException;
import com.example.hackathon6backend.global.feign.exception.FeignUnauthorizedException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        var exception = errorDecoder.decode(s, response);

        if(response != null && response.status() >= 400) {
            switch (response.status()) {
                case 400:
                    return FeignBadRequestException.EXCEPTION;
                case 401:
                    return FeignUnauthorizedException.EXCEPTION;
                case 403:
                    return FeignForbiddenException.EXCEPTION;
                default:
                    if (response.status() >= 500) {
                        return new RetryableException(
                            response.status(),
                            exception.getMessage(),
                            response.request().httpMethod(),
                            exception,
                            (Long) null,
                            response.request()
                        );
                    }
                    break;
            }

        }
        return exception;
    }
}
