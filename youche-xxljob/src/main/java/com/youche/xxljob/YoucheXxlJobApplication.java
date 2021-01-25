package com.youche.xxljob;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class YoucheXxlJobApplication {

    public static void main(String[] args) {
        Main.run(YoucheXxlJobApplication.class, args);
    }
}
