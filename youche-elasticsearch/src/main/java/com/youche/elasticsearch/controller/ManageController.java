package com.youche.elasticsearch.controller;

import com.youche.elasticsearch.utils.IndexSettings;
import com.youche.utils.Result;
import com.youche.utils.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log4j2
@RequestMapping("manage")
@RestController
public class ManageController {


    private RestHighLevelClient restHighLevelClient;

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @GetMapping("createIndex")
    public ResponseEntity<Result<String>> createIndex(String indexName, int shards, int replicas) {
        if (indexName == null) {
            return ResultUtils.error();
        }

        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.settings(IndexSettings.instance()
                .setGroupSettings(shards, replicas).getSettingsBuilder());

        try {
            CreateIndexResponse response = restHighLevelClient.indices()
                    .create(createIndexRequest, RequestOptions.DEFAULT);

            String index = response.index();
            return ResultUtils.ok(index);
        } catch (IOException e) {
            log.error("", e);
        }

        return ResultUtils.error();
    }

    @GetMapping("deleteIndex")
    public ResponseEntity<Result<String>> deleteIndex(String indexName) {
        if (indexName == null) {
            return ResultUtils.error();
        }

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);

        try {
            AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged =  acknowledgedResponse.isAcknowledged();
            return ResultUtils.message(acknowledged);
        } catch (IOException e) {
            log.error("", e);
        }

        return ResultUtils.error();
    }

    // 注意，只能修改副本，不能修改分片，分片只能先删除索引，重建整个索引
    @GetMapping("resetIndexReplicas")
    public ResponseEntity<Result<String>> resetIndexReplicas(String indexName, int replicas) {
        if (indexName == null) {
            return ResultUtils.error();
        }

        UpdateSettingsRequest updateSettingsRequest = new UpdateSettingsRequest(indexName);
        updateSettingsRequest.settings(IndexSettings.instance()
                .updateReplicas(replicas).getSettingsBuilder());

        try {
            AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().putSettings(updateSettingsRequest, RequestOptions.DEFAULT);
            boolean acknowledged = acknowledgedResponse.isAcknowledged();
            return ResultUtils.message(acknowledged, String.format("索引[%s] 副本变更为 %s 操作成功", indexName, replicas));
        } catch (IOException e) {
            log.error("", e);
        }

        return ResultUtils.error();
    }
}
