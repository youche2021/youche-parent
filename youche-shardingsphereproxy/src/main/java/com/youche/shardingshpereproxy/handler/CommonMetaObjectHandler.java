package com.youche.shardingshpereproxy.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段自动填充
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    private static final String FIELD_CREATE_TIME = "createTime";

    private static final String FIELD_UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        Date currentDate = new Date();
        if (metaObject.hasSetter(FIELD_CREATE_TIME)) {
            this.setFieldValByName(metaObject, FIELD_CREATE_TIME, Date.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
