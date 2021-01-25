package com.youche.influxdb.controller;

import com.youche.influxdb.model.OrderPayment;
import com.youche.influxdb.template.InfluxMapper;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequestMapping("/orderPayment")
@RestController
public class OrderPaymentController {

    private InfluxMapper influxMapper;

    @Autowired
    public void setInfluxMapper(InfluxMapper influxMapper) {
        this.influxMapper = influxMapper;
    }

    @PostMapping("save")
    public ResponseEntity<Result<OrderPayment>> save(OrderPayment product) {
        influxMapper.save(product);
        return ResultUtils.ok();
    }

    @GetMapping("queryAll")
    public ResponseEntity<Result<List<OrderPayment>>> queryAll() {
        List<OrderPayment> products = influxMapper.query(OrderPayment.class);
        return ResultUtils.ok(products);
    }

    @GetMapping("queryByBu")
    public ResponseEntity<Result<List<OrderPayment>>> queryByBu(String bu) {
        String sql = String.format("%s'%s'", "select * from m_order_payment where bu_id = ", bu);
        log.error(sql);
        Query query = new Query(sql, "yeesoft");
        List<OrderPayment> products = influxMapper.query(query, OrderPayment.class);
        return ResultUtils.ok(products);
    }
}
