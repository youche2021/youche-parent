package com.youche.flink.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youche.flink.repository.dao.UserMapper;
import com.youche.flink.repository.dataobject.User;
import com.youche.flink.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
