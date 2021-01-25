package com.youche.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kailo.elasticsearch")
public class ElasticSearchProperties {

    private String host;
    private int port;
}
