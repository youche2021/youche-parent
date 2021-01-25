package com.youche.shardingshperejdbc;

import com.youche.utils.springboot.Main;
import com.youche.swagger.starter.autoconfigure.EnableYoucheSwagger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableYoucheSwagger
@SpringBootApplication
public class KailoShardingShpereJdbcApplication {

    public static void main(String[] args) {
        Main.run(KailoShardingShpereJdbcApplication.class, args);
    }
}
