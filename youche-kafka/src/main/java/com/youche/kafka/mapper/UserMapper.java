package com.youche.kafka.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youche.kafka.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
