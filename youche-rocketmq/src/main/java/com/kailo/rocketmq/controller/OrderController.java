package com.kailo.rocketmq.controller;

import com.kailo.rocketmq.Constants;
import com.kailo.rocketmq.model.Order;
import com.kailo.rocketmq.producer.RocketMqProducer;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@Api(value = "订单管理", tags = "管理订单增删改查")
@Log4j2
@RestController
@RequestMapping("/order")
public class OrderController {

    private RocketMqProducer rocketMqProducer;

    public OrderController(RocketMqProducer rocketMqProducer) {
        this.rocketMqProducer = rocketMqProducer;
    }

    @ApiOperation(value = "保存或更新订单", notes = "id、productName、amount都是必输入项")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", dataTypeClass = Long.class, required = true),
            @ApiImplicitParam(name = "productName", value = "", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "amount", value = "", dataTypeClass = BigDecimal.class, required = true)
    })
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Result<Order>> saveOrUpdate(
            @RequestParam Long id,
            @RequestParam String productName,
            @RequestParam BigDecimal amount
    ) {
        Order order = new Order(id, productName, amount, new Date());
        rocketMqProducer.getRocketMQTemplate().getProducer().setSendMsgTimeout(10000);
        rocketMqProducer.getRocketMQTemplate().convertAndSend(Constants.ROCKET_MQ_ORDER, order);
        return ResultUtils.ok(order);
    }
}
