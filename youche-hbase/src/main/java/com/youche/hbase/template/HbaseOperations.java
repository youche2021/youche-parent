package com.youche.hbase.template;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;

public interface HbaseOperations {

    <T> T execute(String tableName, TableCallback<T> mapper);

    <T> List<T> find(String tableName, String family, final RowMapper<T> mapper);

    <T> List<T> find(String tableName, String family, String qualifier, final RowMapper<T> mapper);

    <T> List<T> find(String tableName, final Scan scan, final RowMapper<T> mapper);

    <T> T get(String tableName, String rowName, final RowMapper<T> mapper);

    <T> T get(String tableName, String rowName, String familyName, final RowMapper<T> mapper);

    <T> T get(String tableName, final String rowName, final String familyName, final String qualifier, final RowMapper<T> mapper);

    void execute(String tableName, MutatorCallback action);

    void saveOrUpdate(String tableName, Mutation mutation);

    void saveOrUpdates(String tableName, List<Mutation> mutations);

    boolean delete(String tableName, String rowName, String family, String qualifier);

    boolean delete(String tableName, String rowName, String family);
}
