package com.youche.influxdb.template;

import org.influxdb.InfluxDB;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;

public class InfluxMapper extends InfluxDBMapper {

    public InfluxMapper(InfluxDB influxDB) {
        super(influxDB);
    }

    public interface InfluxCallback {
        <T> T doCallback(String database, InfluxDB influxDB);
    }

    public interface InfluxInsertCallback {
        void doCallback(String database, InfluxDB influxDB);
    }

    public interface InfluxQueryCallback {
        QueryResult doCallBack(String database, InfluxDB influxDB);
    }
}
