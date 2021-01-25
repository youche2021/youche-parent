package com.kailo.rocketmq.consumer;

import com.kailo.rocketmq.Constants;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Log4j2
@Component
@RocketMQMessageListener(topic = Constants.MESSAGE_KEY, selectorExpression = "*", consumerGroup = Constants.PRODUCER_GROUP)
public class RocketMqConsumerListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        String message = new String(body);


        log.error(message);
    }
}
