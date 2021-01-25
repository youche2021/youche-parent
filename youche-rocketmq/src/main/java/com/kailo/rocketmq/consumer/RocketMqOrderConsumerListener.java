package com.kailo.rocketmq.consumer;

import com.kailo.rocketmq.Constants;
import com.kailo.rocketmq.model.Order;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RocketMQMessageListener(topic = Constants.ROCKET_MQ_ORDER, selectorExpression = "*", consumerGroup = Constants.ROCKET_MQ_CONSUMER_GROUP_ORDER)
public class RocketMqOrderConsumerListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.error("【onMessage start】");
        log.error(order.getId());
        log.error(order.getProductName());
        log.error(order.getAmount());
        log.error(order.getCreateTime());
        log.error("【onMessage end】");
    }
}
