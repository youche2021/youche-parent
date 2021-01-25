package com.youche.kafka.config;

import com.youche.kafka.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return new NewTopic(Constants.TOPIC_USER, 1, (short) 1);
    }
}
