package com.youche.hbase.controller;

import com.youche.hbase.model.Product;
import com.youche.hbase.service.ProductService;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("queryByRowKey")
    public ResponseEntity<Result<Product>> queryByRowKey(String rowKey) {
        Product product = productService.query(rowKey);
        log.error(product);
        return ResultUtils.ok(product);
    }
}
