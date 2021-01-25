package com.youche.shardingshperejdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youche.shardingshperejdbc.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
