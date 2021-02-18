package com.youche.flink.controller;

import com.alibaba.fastjson.JSON;
import com.youche.flink.Constants;
import com.youche.flink.controller.request.AddOrUpdateUserRequest;
import com.youche.flink.repository.dataobject.User;
import com.youche.flink.service.UserService;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * 发送程序
     * @return
     * @throws
     */
    @PostMapping("/create")
    // Swagger 需指定类型，不然非字符串类型会报转换错误
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "用户名", defaultValue = "请输入用户名")
    })
    public ResponseEntity<Result<String>> create(@Valid AddOrUpdateUserRequest addOrUpdateUserRequest) {
        User user = new User();
        BeanUtils.copyProperties(addOrUpdateUserRequest, user);

        userService.saveOrUpdate(user);

        kafkaTemplate.send(Constants.TOPIC_USER, JSON.toJSONString(user));
        return ResultUtils.ok("发送成功");
    }
}
