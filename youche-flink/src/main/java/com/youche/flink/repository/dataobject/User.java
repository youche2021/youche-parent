package com.youche.flink.repository.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "用户实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_base_user")
public class User implements Serializable {

    @ApiModelProperty(value = "主键", allowEmptyValue = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String userName;

    @NotNull(message = "备注不能为空")
    @ApiModelProperty(value = "备注")
    private String description;

    @NotNull(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickName;

    // 自动新增
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    // 自动更新
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
