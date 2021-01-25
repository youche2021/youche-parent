package com.youche.elasticsearch.sql.config;

import com.youche.utils.springboot.Main;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

/**
 * 如果打War包，在tomcat启动，需要继承 SpringBootServletInitializer 实现 configure
 */
@Log4j2
@Component
public class WarSpringBootServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.getApplicationClazz());
    }
}
