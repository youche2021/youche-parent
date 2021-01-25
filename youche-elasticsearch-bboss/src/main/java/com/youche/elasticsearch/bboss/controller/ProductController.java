package com.youche.elasticsearch.bboss.controller;

import com.youche.elasticsearch.bboss.model.Product;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.frameworkset.elasticsearch.entity.sql.SQLResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequestMapping("product")
@RestController
public class ProductController {

    private BBossESStarter bbossESStarter;

    @Autowired
    public void setBbossESStarter(BBossESStarter bbossESStarter) {
        this.bbossESStarter = bbossESStarter;
    }

    @GetMapping("queryAllUseSearch")
    public ResponseEntity<Result<List<Product>>> queryAllUseSearch() {
        ClientInterface clientInterface = bbossESStarter.getRestClient();

        ESDatas<Product> productESDatas = clientInterface.searchList("es_biz_trade_product/_search", Product.class);
        log.error("TotalSize: {}", productESDatas.getTotalSize());
        log.error("ScrollId: {}", productESDatas.getScrollId());
        log.error("TotalRelation: {}", productESDatas.getTotalRelation());
        return ResultUtils.ok(productESDatas.getDatas());
    }

    @GetMapping("queryAllUseExecuteHttp")
    public ResponseEntity<Result<List<Product>>> queryAllUseExecuteHttp() {
        ClientInterface clientInterface = bbossESStarter.getRestClient();
        String json = clientInterface.executeHttp("/_xpack/sql?format=json", "{\"query\": \"SELECT * FROM es_biz_trade_product\"}", ClientInterface.HTTP_POST);
        return ResultUtils.ok(json);
    }

    @GetMapping("queryAllUseFetchQuery")
    public ResponseEntity<Result<List<Product>>> queryAllUseFetchQuery() {
        ClientInterface clientInterface = bbossESStarter.getRestClient();
        SQLResult<Product> productSQLResult = clientInterface.fetchQuery(Product.class, "{\"query\": \"SELECT * FROM es_biz_trade_product\"}");

        return ResultUtils.ok(productSQLResult.getDatas());
    }

    @GetMapping("queryAllUseSql")
    public ResponseEntity<Result<List<Product>>> queryAllUseSql() {
        ClientInterface clientInterface = bbossESStarter.getRestClient();
        List<Product> productList = clientInterface.sql(Product.class,"{\"query\": \"SELECT * FROM es_biz_trade_product\"}");
        return ResultUtils.ok(productList);
    }
}

// ElasticSearchHelper.getRestClientUtil();