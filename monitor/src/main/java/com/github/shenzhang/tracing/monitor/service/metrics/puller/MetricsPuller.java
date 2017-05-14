package com.github.shenzhang.tracing.monitor.service.metrics.puller;

import com.github.shenzhang.tracing.monitor.domain.metrics.Metrics;
import com.github.shenzhang.tracing.monitor.service.metrics.MetricsCollector;

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
        if (metricss != null) {
            this.metricsCollector.collect(metricss);
        }
    }
}
