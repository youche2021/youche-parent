package com.youche.flink;

import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import com.youche.utils.springboot.Main;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class YoucheFlinkApplication {

    public static void main(String[] args) {
        Main.run(YoucheFlinkApplication.class, args);
    }
}
