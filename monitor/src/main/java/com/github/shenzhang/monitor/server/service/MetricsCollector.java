package com.github.shenzhang.monitor.server.service;

import com.github.shenzhang.monitor.server.dao.MetricsDao;
import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private static final int BUFFER_SIZE = 100;

    private List<Metrics> buffer = newBuffer();
    private Object bufferLock = new Object();
    private List<Metrics> pending;

    @Autowired
    private MetricsDao metricsDao;

    public void collect(List<Metrics> metricss) {
        if (metricss == null || metricss.isEmpty()) {
            return;
        }

        synchronized (bufferLock) {
            buffer.addAll(metricss);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void flush() {
        synchronized (bufferLock) {
            if (buffer.isEmpty()) {
                return;
            }

            if (pending == null) {
                pending = buffer;
            } else {
                pending.addAll(buffer);
            }

            buffer = newBuffer();
        }

        try {
            metricsDao.addMetrics(pending);
            pending = null;
        } catch (DaoException e) {
            LOGGER.error("Flush metrics failed. {}:{}", e.getClass().getName(), e.getMessage());
        }
    }

    private List<Metrics> newBuffer() {
        return new ArrayList<>(BUFFER_SIZE);
    }
}
