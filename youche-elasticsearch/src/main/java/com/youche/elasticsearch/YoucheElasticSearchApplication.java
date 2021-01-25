package com.youche.elasticsearch;

import com.youche.utils.springboot.Main;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class YoucheElasticSearchApplication {

    public static void main(String[] args) {
        Main.run(YoucheElasticSearchApplication.class, args);
    }
}
