package com.github.shenzhang.monitor.server.metrics.puller;

import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.service.MetricsCollector;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 9:18 AM.
 */
public abstract class MetricsPuller implements Runnable {
    private MetricsCollector metricsCollector;

    public void setMetricsCollector(MetricsCollector metricsCollector) {
        this.metricsCollector = metricsCollector;
    }

    protected void collect(List<Metrics> metricss) {
        if (metricss != null && !metricss.isEmpty()) {
            this.metricsCollector.collect(metricss);
        }
    }
}
