package com.youche.kafka;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableYoucheSwagger
@EnableKafka
@SpringBootApplication
public class YoucheKafkaApplication {

    public static void main(String[] args) {
        Main.run(YoucheKafkaApplication.class, args);
    }
}
