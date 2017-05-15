package com.github.shenzhang.monitor.server.configuration.metrics;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/13/17
 * Time: 11:40 AM.
 */
@Configuration
public class InfluxDbConfiguration {
    @Autowired
    private MetricsPullerProperties metricsPullerProperties;

    @Bean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(metricsPullerProperties.getInfluxdb().getUrl());
        return influxDB;
    }
}
