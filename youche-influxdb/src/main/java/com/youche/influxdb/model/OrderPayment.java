package com.youche.influxdb.model;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;

@Data
@Measurement(name = "m_order_payment", database = "yeesoft", retentionPolicy = "rp_order_payment")
public class OrderPayment implements Serializable  {

    // 统计批次
    @Column(name = "batch_id", tag = true)
    private String batchId;

    // 哪个BU
    @Column(name = "bu_id", tag = true)
    private String buId;

    // BU 名称
    @Column(name = "bu_name")
    private String buName;

    // 总数
    @Column(name = "total_count", tag = true)
    private String totalCount;

    // 支付量
    @Column(name = "pay_count", tag = true)
    private String payCount;

    // 金额
    @Column(name = "total_money", tag = true)
    private String totalMoney;
}
