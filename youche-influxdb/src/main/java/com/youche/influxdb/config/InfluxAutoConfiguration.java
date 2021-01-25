package com.youche.influxdb.config;


import com.youche.influxdb.template.InfluxMapper;
import lombok.extern.log4j.Log4j2;
import org.influxdb.InfluxDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class InfluxAutoConfiguration {

    @Bean
    public InfluxMapper influxMapper(InfluxDB influxDB) {
        InfluxMapper influxMapper = new InfluxMapper(influxDB);
        return influxMapper;
    }
}
