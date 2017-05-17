package com.github.shenzhang.monitor.agent.metrics;

import com.github.shenzhang.monitor.agent.domain.Metrics;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 2:57 PM.
 */
@Component
public class SystemMetricsCollector implements MetricsCollector {
    private static final String MEASUREMENT = "system";

    @Override
    public Metrics collect() {
        Metrics metrics = new Metrics();
        metrics.setMeasurement(MEASUREMENT);

        memory(metrics);
        system(metrics);

        return metrics;
    }

    private void memory(Metrics metrics) {
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        long used = heapMemoryUsage.getUsed() / 1024 / 1024;
        long max = heapMemoryUsage.getMax() / 1024 / 1024;
        metrics.addField("memUsed", used).addField("memMax", max);
    }

    private void system(Metrics metrics) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        metrics.addField("systemLoad", operatingSystemMXBean.getSystemLoadAverage());
    }
}
