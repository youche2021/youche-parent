package com.youche.elasticsearch.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private long id;
    private String name;
    private double originalPrice;
    private double currentPrice;
}
