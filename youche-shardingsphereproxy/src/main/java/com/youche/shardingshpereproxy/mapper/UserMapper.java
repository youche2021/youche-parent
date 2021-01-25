package com.youche.shardingshpereproxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youche.shardingshpereproxy.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
