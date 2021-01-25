package com.youche.redis.controller;

import com.youche.redis.model.User;
import com.youche.redis.service.UserService;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Api(value = "首页")
@RestController
public class IndexController {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserService userService;

    public IndexController(RedisTemplate<String, String> redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }

    @ApiOperation(value = "查询API")
    @GetMapping("/search")
    public ResponseEntity<Result<String>> search( @ApiParam(value = "查询参数") @RequestParam String keywords ) {

        String redisKey = keywords;
        String result;

        if (redisTemplate.hasKey(redisKey)) {
            log.error("get redis");
            result = redisTemplate.opsForValue().get(redisKey);
        } else {
            log.error("set redis");
            try {
                // 模拟慢场景
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = String.format("param: %s", keywords);
            redisTemplate.opsForValue().set(redisKey, keywords, 30000L);
        }

        return ResultUtils.ok(result);
    }

    @ApiOperation(value = "新增或修改用户")
    @PostMapping("/saveOrUpdateUser")
    public ResponseEntity<Result<User>> saveOrUpdateUser(
            @ApiParam(value = "用户ID") @RequestParam Long id,
            @ApiParam(value = "用户姓名") @RequestParam String name
    ) {
        User user = userService.saveOrUpdateUser(id, name);
        return ResultUtils.ok(user, "保存成功");
    }

    @ApiOperation(value = "查询用户")
    @GetMapping("/getUserById")
    public ResponseEntity<Result<User>> getUserById(
            @ApiParam(value = "用户ID") @RequestParam Long id
    ) {
        User user = userService.getUser(id);
        return ResultUtils.ok(user, "查询成功");
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/deleteById")
    public ResponseEntity<Result<Long>> deleteById(
            @ApiParam(value = "用户ID") @RequestParam Long id
    ) {
        userService.delete(id);
        return ResultUtils.ok(id, "删除成功");
    }

}