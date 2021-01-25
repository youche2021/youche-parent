package com.youche.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "网站")
@RestController
public class WebSiteController {

    @ApiOperation(value = "检索", notes = "index")
    @GetMapping(value = "/search")
    public String search(@ApiParam(value = "查询关键字", required = true) @RequestParam String keywords) {
        return String.format("%s-result", keywords);
    }
}