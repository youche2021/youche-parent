package com.youche.mongodb.utils;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
public class ResultUtils implements Serializable {

    public static final int SUCCESS = 200;
    public static final int NOT_FOUND = 404;
    public static final int ERROR = 500;

    public static final String OPT_SUCCESS_LANG = "操作成功";
    public static final String OPT_ERROR_LANG = "操作失败";

    public static <T> ResponseEntity<Result<T>> ok() {
        return ok(OPT_SUCCESS_LANG);
    }

    public static <T> ResponseEntity<Result<T>> ok(String message) {
        return ok(null, message);
    }

    public static <T> ResponseEntity<Result<T>> ok(T data) {
        return ok(data, null);
    }

    public static <T> ResponseEntity<Result<T>> ok(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return ResponseEntity.ok(result);
    }


    public static <T> ResponseEntity<Result<T>> error() {
        return ok(OPT_ERROR_LANG);
    }

    public static <T> ResponseEntity<Result<T>> error(String message) {
        return ok(null, message);
    }

    public static <T> ResponseEntity<Result<T>> error(T data) {
        return ok(data, null);
    }

    public static <T> ResponseEntity<Result<T>> error(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(ERROR);
        result.setMessage(message);
        result.setData(data);
        return ResponseEntity.ok(result);
    }
}
