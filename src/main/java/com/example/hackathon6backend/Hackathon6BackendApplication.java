package com.example.hackathon6backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Hackathon6BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hackathon6BackendApplication.class, args);
    }

}
