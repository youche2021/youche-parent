package com.youche.hbase.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private String name;
    private String user;
    private Double ccc;
    private Double width;
    private Float height;
}
