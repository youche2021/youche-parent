package com.youche.flink.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "新增或修改用户")
public class AddOrUpdateUserRequest implements Serializable {

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
}
