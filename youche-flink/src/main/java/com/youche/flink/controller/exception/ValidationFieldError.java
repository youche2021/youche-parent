package com.youche.flink.controller.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationFieldError implements Serializable {
    private String field;
    private String error;
}
