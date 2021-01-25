package com.youche.influxdb.template;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * InfluxDB
 *
 */
@Component
public class InfluxTemplate {

    private InfluxDB influxDB;

    @Autowired
    public void setInfluxDB(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    // 查单个

    // 查所有

    public List<Map> findMaps(String sql) {
        Query query = new Query(sql);
        QueryResult queryResult = this.influxDB.query(query);
        List<QueryResult.Result> resultList = queryResult.getResults();

        List<QueryResult.Series> seriesList;
        List<String> columnNameList;
        for (QueryResult.Result result : resultList) {
            seriesList = result.getSeries();
            for (QueryResult.Series series : seriesList) {
                columnNameList = series.getColumns();
                for (String columnName : columnNameList) {
                    System.out.println(columnName);
                }
            }
        }

        return null;
    }

    // 分页

    // 查对象
    public <T> List<T> findBeans(String sql, Class<T> beanClass) {
        return null;
    }

    // 新增
    public boolean insert(String measurement, Map<String, String> tagsToAdd, Map<String, Object> fieldsToAdd) {
        if (StringUtils.isEmpty(measurement) || fieldsToAdd == null || tagsToAdd == null) {
            return false;
        }

        Point.Builder builder = Point.measurement(measurement).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        builder.tag(tagsToAdd);
        builder.fields(fieldsToAdd);

        this.influxDB.write(builder.build());
        return true;
    }

    // 删除指标

    // 设置策略

    // 设置连续查询
}
