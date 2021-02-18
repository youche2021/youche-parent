package com.youche.flink.repository.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataChangedMetaObjectHandler implements MetaObjectHandler {

    private static final String FIELD_CREATE_TIME = "createTime";

    private static final String FIELD_UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter(FIELD_CREATE_TIME)) {
            Date currentDate = new Date();
            this.setFieldValByName(FIELD_CREATE_TIME, currentDate, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter(FIELD_UPDATE_TIME)) {
            Date currentDate = new Date();
            this.setFieldValByName(FIELD_UPDATE_TIME, currentDate, metaObject);
        }
    }
}
