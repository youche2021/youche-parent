package com.youche.swagger.starter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kailo.swagger")
public class YoucheSwaggerProperties {

    private String title;
    private String description;
    private String version;
}
