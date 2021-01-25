package com.youche.redis;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableYoucheSwagger
// pom导入了 spring cache starter，可以打开spring 缓存
@EnableCaching
@Log4j2
@SpringBootApplication
public class YoucheApplication {

    public static void main(String[] args) {
        Main.run(YoucheApplication.class, args);
    }
}
