package com.youche.elasticsearch.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Log4j2
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(value = ElasticSearchProperties.class)
@ConditionalOnClass(value = ElasticSearchProperties.class)
public class ElasticSearchAutoConfiguration {

    private ElasticSearchProperties elasticSearchProperties;

    @Autowired
    public void setElasticSearchProperties(ElasticSearchProperties elasticSearchProperties) {
        this.elasticSearchProperties = elasticSearchProperties;
    }
}
