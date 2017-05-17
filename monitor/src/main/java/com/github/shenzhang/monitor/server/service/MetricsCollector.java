package com.github.shenzhang.monitor.server.service;

import com.github.shenzhang.monitor.server.dao.MetricsDao;
import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Collect and cache metrics. Persist metrics in batch way.
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
        try {
            metricsDao.addMetrics(metricss);
        } catch (DaoException e) {
            LOGGER.error("Add metrics failed. {}:{}", e.getClass().getName(), e.getMessage());
        }
    }
}
