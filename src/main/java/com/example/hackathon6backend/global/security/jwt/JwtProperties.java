package com.example.hackathon6backend.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {

    private final String secretKey;
    private final Long accessExp;
    private final String header;
    private final String prefix;

}
