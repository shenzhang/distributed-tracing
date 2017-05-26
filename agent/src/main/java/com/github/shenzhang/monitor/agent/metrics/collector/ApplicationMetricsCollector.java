package com.github.shenzhang.monitor.agent.metrics.collector;

import com.github.shenzhang.monitor.agent.domain.Metrics;
import com.github.shenzhang.monitor.agent.metrics.CountMetricsStore;
import com.github.shenzhang.monitor.agent.reporter.MetricsReporter;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 3:24 PM.
 */
public class ApplicationMetricsCollector implements MetricsCollector {
    private static final String MEASUREMENT = "application";

    @Autowired
    private TracingRepository tracingRepository;
    @Autowired
    private CountMetricsStore countMetricsStore;
    @Autowired
    private MetricsReporter metricsReporter;

    @Override
    public Metrics collect() {
        Metrics metrics = new Metrics();
        metrics.setMeasurement(MEASUREMENT);

        tracingBuffer(metrics);
        metricsBuffer(metrics);

        serviceCount(metrics);

        return metrics;
    }

    private void metricsBuffer(Metrics metrics) {
        metrics.addField("metricsBuffer", metricsReporter.getCurrentBufferSize());
    }

    private void tracingBuffer(Metrics metrics) {
        metrics.addField("tracingBuffer", tracingRepository.count());
    }

    private void serviceCount(Metrics metrics) {
        for (String name : countMetricsStore.getAllMetricsName()) {
            metrics.addField(name, countMetricsStore.set(name, 0));
        }
    }
}
