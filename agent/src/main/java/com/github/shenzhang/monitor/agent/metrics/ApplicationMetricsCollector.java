package com.github.shenzhang.monitor.agent.metrics;

import com.github.shenzhang.monitor.agent.domain.Metrics;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 3:24 PM.
 */
@Component
public class ApplicationMetricsCollector implements MetricsCollector {
    private static final String MEASUREMENT = "application";

    @Autowired
    private TracingRepository tracingRepository;

    @Override
    public Metrics collect() {
        Metrics metrics = new Metrics();
        metrics.setMeasurement(MEASUREMENT);

        tracing(metrics);

        return metrics;
    }

    private void tracing(Metrics metrics) {
        metrics.addField("tracingBuffer", tracingRepository.count());
    }
}
