package com.kailo.rocketmq;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class YoucheRocketmqApplication {

    public static void main(String[] args) {
        Main.run(YoucheRocketmqApplication.class, args);
    }
}
