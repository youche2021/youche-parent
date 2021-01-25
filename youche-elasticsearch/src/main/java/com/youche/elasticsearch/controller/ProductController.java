package com.youche.elasticsearch.controller;

import com.youche.elasticsearch.model.Product;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import com.youche.utils.convert.BeanConvertUtils;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@RequestMapping("product")
@RestController
public class ProductController {

    private RestClient restClient;

    @Autowired
    public void setRestClient(@Qualifier("elasticsearchRestClient") RestClient elasticsearchRestClient) {
        this.restClient = elasticsearchRestClient;
    }

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @GetMapping("queryByName")
    public ResponseEntity<Result<List<Product>>> queryByName(int size, String name) {

        String index = "es_biz_trade_product";
        SearchRequest searchRequest = new SearchRequest();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(size);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name);
        boolQueryBuilder.must(matchQueryBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.indices(index).source(searchSourceBuilder);

        try {

            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List<Product> products = new ArrayList<>();
            searchResponse.getHits().iterator().forEachRemaining(hit -> {
                Map<String, Object> responseMap = hit.getSourceAsMap();
                Product product = BeanConvertUtils.mapToBean(Product.class, responseMap);
                products.add(product);
            });
            return ResultUtils.ok(products);

        } catch (IOException e) {
            log.error("", e);
        }

        return ResultUtils.ok();
    }

    @GetMapping("queryByEsId")
    public ResponseEntity<Result<Product>> queryByEsId(String esId) {

        if (StringUtils.isEmpty(esId)) {
            return ResultUtils.ok(null);
        }

        GetRequest getRequest = new GetRequest("es_biz_trade_product", esId);

        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

            if (getResponse.isExists()) {
                Map<String, Object> responseMap = getResponse.getSourceAsMap();
                Product product = BeanConvertUtils.mapToBean(Product.class, responseMap);
                return ResultUtils.ok(product);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultUtils.ok(null);
    }

    @GetMapping("queryAll")
    public ResponseEntity<Result<List<Product>>> queryAll() {
        SearchRequest searchRequest = new SearchRequest("es_biz_trade_product").searchType("_search");

        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder("", "");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        return null;
    }
}
