package com.github.shenzhang.tracing.monitor.service.metrics;

import com.github.shenzhang.tracing.monitor.dao.MetricsDao;
import com.github.shenzhang.tracing.monitor.domain.metrics.Metrics;
import com.github.shenzhang.tracing.monitor.domain.metrics.SystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 8:27 PM.
 */
@Component
public class MetricsCollector {
    private static Logger LOGGER = LoggerFactory.getLogger(MetricsCollector.class);

    @Autowired
    private MetricsDao metricsDao;

    public void collect(List<Metrics> metricss) {
        for (Metrics metrics : metricss) {
            if (metrics instanceof SystemMetrics) {
                try {
                    metricsDao.addSystemMetrics(newArrayList((SystemMetrics) metrics));
                } catch (Exception e) {
                    LOGGER.error("Add metrics failed, measurement={}. {}:{}", metrics.getMeasurement(),
                            e.getClass().getName(), e.getMessage());
                }
            }
        }
    }
}
