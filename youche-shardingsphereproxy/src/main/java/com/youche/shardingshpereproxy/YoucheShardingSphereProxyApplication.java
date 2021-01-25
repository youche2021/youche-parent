package com.youche.shardingshpereproxy;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class YoucheShardingSphereProxyApplication {

    public static void main(String[] args) {
        Main.run(YoucheShardingSphereProxyApplication.class, args);
    }
}
