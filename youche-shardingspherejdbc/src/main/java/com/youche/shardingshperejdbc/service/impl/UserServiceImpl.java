package com.youche.shardingshperejdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youche.shardingshperejdbc.mapper.UserMapper;
import com.youche.shardingshperejdbc.model.User;
import com.youche.shardingshperejdbc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
