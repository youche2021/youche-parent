package com.youche.hbase.template;

import org.apache.hadoop.hbase.client.Result;

public interface RowMapper<T> {

    T mapRow(Result result, int rowNum) throws Exception;
}
