package com.youche.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.youche.kafka.Constants;
import com.youche.kafka.model.User;
import com.youche.kafka.producer.UserProducer;
import com.youche.kafka.service.UserService;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "用户管理")
@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    private UserProducer userProducer;
    private UserService userService;

    public UserController(UserProducer userProducer, UserService userService) {
        this.userProducer = userProducer;
        this.userService = userService;
    }

    @ApiOperation(value = "保存或更新用户", notes = "userName是必输入项")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "用户主键", dataTypeClass = Long.class, required = false),
            @ApiImplicitParam(name = "userName", value = "用户姓名", dataTypeClass = String.class, required = true)
    })
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Result<User>> saveOrUpdate(
            @RequestParam(required = false) Long id,
            @RequestParam String userName
    ) {
        User user = new User(id, userName, new Date());
        userService.saveOrUpdate(user);
        userProducer.getKafkaTemplate().send(Constants.TOPIC_USER, JSON.toJSONString(user).toString());
        return ResultUtils.ok(user);
    }

    @ApiOperation(value = "查询所有用户", notes = "")
    @GetMapping("/queryAll")
    public ResponseEntity<Result<List<User>>> queryAll() {
        List<User> userList = userService.list();
        return ResultUtils.ok(userList, "查询成功");
    }
}