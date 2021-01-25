package com.kailo.rocketmq.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(description= "订单实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "价格")
    private BigDecimal amount;
    @ApiModelProperty(value = "时间")
    private Date createTime;
}
