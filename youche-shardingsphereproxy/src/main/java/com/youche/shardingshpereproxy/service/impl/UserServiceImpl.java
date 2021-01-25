package com.youche.shardingshpereproxy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youche.shardingshpereproxy.mapper.UserMapper;
import com.youche.shardingshpereproxy.model.User;
import com.youche.shardingshpereproxy.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
