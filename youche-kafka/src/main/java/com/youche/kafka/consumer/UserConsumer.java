package com.youche.kafka.consumer;

import com.youche.kafka.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserConsumer {

    @KafkaListener(topics = {Constants.TOPIC_USER}, groupId = Constants.GROUP_USER)
    public void onMessage(String message) {
        log.error("[onMessage start]");
        log.error(message);
        log.error("[onMessage end]");
    }
}
