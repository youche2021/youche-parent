package com.youche.hbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.hbase")
public class HbaseProperties {

    private String quorum;
    private String rootDir;
    private String nodeParent;
    private boolean prepareConnection;
}
