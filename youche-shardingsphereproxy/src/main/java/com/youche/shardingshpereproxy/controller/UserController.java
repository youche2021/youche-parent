package com.youche.shardingshpereproxy.controller;

import com.youche.shardingshpereproxy.model.User;
import com.youche.shardingshpereproxy.service.UserService;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "用户管理")
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

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
        return ResultUtils.ok(user);
    }

    @ApiOperation(value = "查询所有用户", notes = "")
    @GetMapping("/queryAll")
    public ResponseEntity<Result<List<User>>> queryAll() {
        List<User> userList = userService.list();
        return ResultUtils.ok(userList, "查询成功");
    }
}