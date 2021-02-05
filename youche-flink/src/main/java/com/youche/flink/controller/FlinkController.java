package com.youche.flink.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlinkController {

    @GetMapping("/work")
    public String work() throws InterruptedException {
        // 模拟复杂业务耗时处理流程
        Thread.sleep(10 * 1000L);
        return "success";
    }
}
