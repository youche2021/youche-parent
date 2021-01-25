package com.youche.hbase.template;

import org.apache.hadoop.hbase.client.Table;

public interface TableCallback<T> {

    T doInTable(Table table) throws Throwable;
}
