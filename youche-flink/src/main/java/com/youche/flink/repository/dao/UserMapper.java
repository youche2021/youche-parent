package com.youche.flink.repository.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youche.flink.repository.dataobject.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
