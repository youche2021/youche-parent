package com.youche.flink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metric implements Serializable {
    public String name;
    public long timestamp;
    public Map<String, Object> fields;
    public Map<String, String> tags;
}
