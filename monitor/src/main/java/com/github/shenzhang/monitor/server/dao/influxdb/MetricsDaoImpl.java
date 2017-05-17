package com.github.shenzhang.monitor.server.dao.influxdb;

import com.github.shenzhang.monitor.server.configuration.metrics.MetricsPullerProperties;
import com.github.shenzhang.monitor.server.dao.MetricsDao;
import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.exception.DaoException;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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
    public void addMetrics(List<Metrics> metricss) throws DaoException {
        BatchPoints batchPoints = BatchPoints.database(database)
                .retentionPolicy("autogen")
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        for (Metrics metrics : metricss) {
            if (metrics.getFields() == null || metrics.getFields().isEmpty()) {
                continue;
            }

            Point.Builder pointBuilder = Point.measurement(metrics.getMeasurement())
                    .time(metrics.getTime(), TimeUnit.MILLISECONDS)
                    .tag("app", metrics.getApplication()).tag("host", metrics.getHost());

            for (Map.Entry<String, Double> field : metrics.getFields().entrySet()) {
                pointBuilder.addField(field.getKey(), field.getValue());
            }

            batchPoints.point(pointBuilder.build());
        }

        influxDB.write(batchPoints);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.database = this.properties.getInfluxdb().getDatabase();
    }
}
