package com.youche.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public KafkaTemplate<String, String> getKafkaTemplate() {
        return kafkaTemplate;
    }
}
