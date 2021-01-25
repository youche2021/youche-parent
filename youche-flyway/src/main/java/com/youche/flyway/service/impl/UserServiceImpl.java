package com.youche.flyway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youche.flyway.mapper.UserMapper;
import com.youche.flyway.model.User;
import com.youche.flyway.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
