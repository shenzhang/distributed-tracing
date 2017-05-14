package com.github.shenzhang.tracing.monitor.dao.influxdb;

import com.github.shenzhang.tracing.monitor.configuration.metrics.MetricsPullerProperties;
import com.github.shenzhang.tracing.monitor.dao.MetricsDao;
import com.github.shenzhang.tracing.monitor.domain.metrics.SystemMetrics;
import com.github.shenzhang.tracing.monitor.exception.DaoException;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: Zhang Shen
 * Date: 5/13/17
 * Time: 11:58 AM.
 */
@Repository
public class MetricsDaoImpl implements MetricsDao, InitializingBean {
    @Autowired
    private MetricsPullerProperties properties;
    @Autowired
    private InfluxDB influxDB;
    private String database;

    @Override
    public void addSystemMetrics(List<SystemMetrics> metricsList) throws DaoException {
        BatchPoints batchPoints = BatchPoints.database(database)
                .retentionPolicy("autogen")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        for (SystemMetrics metrics : metricsList) {
            Point point = Point.measurement(metrics.getMeasurement())
                    .time(metrics.getTime().getTime(), TimeUnit.MILLISECONDS)
                    .tag("app", metrics.getApplication()).tag("host", metrics.getHost())
                    .addField("load", metrics.getLoad())
                    .build();

            batchPoints.point(point);
        }

        influxDB.write(batchPoints);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.database = this.properties.getInfluxdb().getDatabase();
    }


}
