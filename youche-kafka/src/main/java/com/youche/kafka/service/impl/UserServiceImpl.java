package com.youche.kafka.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youche.kafka.mapper.UserMapper;
import com.youche.kafka.model.User;
import com.youche.kafka.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
