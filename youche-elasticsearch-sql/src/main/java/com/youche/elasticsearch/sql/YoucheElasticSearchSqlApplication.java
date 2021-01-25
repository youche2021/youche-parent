package com.youche.elasticsearch.sql;

import com.youche.utils.springboot.Main;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class YoucheElasticSearchSqlApplication {

    public static void main(String[] args) {
        Main.run(YoucheElasticSearchSqlApplication.class, args);
    }
}
