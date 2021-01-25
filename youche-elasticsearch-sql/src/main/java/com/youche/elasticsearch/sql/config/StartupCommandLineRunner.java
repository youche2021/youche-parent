package com.youche.elasticsearch.sql.config;

import com.youche.utils.springboot.Main;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StartupCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("[{}] 启动成功!", Main.getApplicationClassName());
    }
}
