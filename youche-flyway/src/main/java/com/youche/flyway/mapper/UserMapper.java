package com.youche.flyway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youche.flyway.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
