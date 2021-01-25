package com.youche.mongodb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(value = "yeesoft")
public class Product implements Serializable  {

    @Id
    @JSONField(name="_id") // fastjson 会过滤 _
    private String _id;
    @Field
    private String name;
    @Field
    private String user;
    @Field
    private Double ccc;

}
