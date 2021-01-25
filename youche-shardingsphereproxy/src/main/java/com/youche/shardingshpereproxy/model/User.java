package com.youche.shardingshpereproxy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "用户实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_base_user")
public class User {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.NONE)
    private Long id;
    @ApiModelProperty(value = "姓名")
    private String userName;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
