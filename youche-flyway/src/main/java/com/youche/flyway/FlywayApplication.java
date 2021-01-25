package com.youche.flyway;

import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class FlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }
}
