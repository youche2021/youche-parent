package com.kailo.rocketmq.producer;

import com.kailo.rocketmq.Constants;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 */
@Service
public class RocketMqProducer {

    private RocketMQTemplate rocketMQTemplate;

    public RocketMqProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public RocketMQTemplate getRocketMQTemplate() {
        return rocketMQTemplate;
    }

    @Value("${rocketmq.producer.send-message-timeout}")
    private Integer messageTimeOut;

    /**
     * 发送普通消息
     * @param messageBody
     */
    public void sendSyncMessage(String messageBody) {
        rocketMQTemplate.syncSend(Constants.MESSAGE_KEY, MessageBuilder.withPayload(messageBody).build());
    }

    /**
     * 发送异步消息
     * @param messageBody
     */
    public void sendAsyncMessage(String messageBody) {
        rocketMQTemplate.asyncSend(Constants.MESSAGE_KEY, MessageBuilder.withPayload(messageBody).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    /**
     * 发送延时消息
     * @param messageBody
     * @param delay
     */
    public void sendDelayMessage(String messageBody, Integer delay) {
        rocketMQTemplate.syncSend(Constants.MESSAGE_KEY, MessageBuilder.withPayload(messageBody).build(), messageTimeOut, delay);
    }

    /**
     * 发送Tag消息
     * @param messageBody
     * @param tag
     */
    public void sendTagMessage(String messageBody, String tag) {
        rocketMQTemplate.syncSend(String.format("%s:%s", Constants.MESSAGE_KEY, tag), MessageBuilder.withPayload(messageBody).build());
    }
}
