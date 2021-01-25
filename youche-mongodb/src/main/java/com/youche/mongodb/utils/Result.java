package com.youche.mongodb.utils;


import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Data
public class Result<T> implements Serializable {
    private int code;
    private String message;
    private T data;
    private long timestamp = Date.from(Instant.now()).getTime();
}