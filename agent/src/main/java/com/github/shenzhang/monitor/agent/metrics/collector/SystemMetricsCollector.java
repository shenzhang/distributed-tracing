package com.github.shenzhang.monitor.agent.metrics.collector;

import com.github.shenzhang.monitor.agent.domain.Metrics;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.*;

import static com.github.shenzhang.monitor.agent.metrics.collector.SystemMetricsCollector.SystemMetrics.*;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 2:57 PM.
 */
public class SystemMetricsCollector implements MetricsCollector {
    interface SystemMetrics {
        String MEM_USED = "memUsed";
        String MEM_MAX = "memMax";
        String SYSTEM_LOAD = "systemLoad";

        String GC_YOUNG_COUNT = "gcYoungCount";
        String GC_YOUNG_TIME = "gcYoungTime";
        String GC_OLD_COUNT = "gcOldCount";
        String GC_OLD_TIME = "gcOldTime";
    }

    private static final String MEASUREMENT = "system";

    private Map<String, Long> lastValue = new HashMap<>(4);

    public SystemMetricsCollector() {
        lastValue.put(GC_YOUNG_COUNT, 0L);
        lastValue.put(GC_YOUNG_TIME, 0L);
        lastValue.put(GC_OLD_COUNT, 0L);
        lastValue.put(GC_OLD_TIME, 0L);
    }

    @Override
    public Metrics collect() {
        Metrics metrics = new Metrics();
        metrics.setMeasurement(MEASUREMENT);

        memory(metrics);
        system(metrics);

        jvmGc(metrics);

        return metrics;
    }

    private void memory(Metrics metrics) {
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();

        long used = heapMemoryUsage.getUsed() / 1024 / 1024;
        long max = heapMemoryUsage.getMax() / 1024 / 1024;
        metrics.addField(MEM_USED, used).addField(MEM_MAX, max);
    }

    private void system(Metrics metrics) {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        metrics.addField(SYSTEM_LOAD, operatingSystemMXBean.getSystemLoadAverage());
    }

    private void jvmGc(Metrics metrics) {
        Set<String> young = new HashSet<>(Arrays.asList("Copy", "PS Scavenge", "ParNew", "G1 Young Generation"));
        Set<String> old = new HashSet<>(Arrays.asList("MarkSweepCompact", "PS MarkSweep", "ConcurrentMarkSweep", "G1 Old Generation"));

        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : gcList) {
            String name = gc.getName();

            String countKey = GC_YOUNG_COUNT;
            String timeKey = GC_YOUNG_TIME;

            if (old.contains(name)) {
                countKey = GC_OLD_COUNT;
                timeKey = GC_OLD_TIME;
            }

            long currentCount = gc.getCollectionCount();
            long currentTime = gc.getCollectionTime();

            long previousCount = lastValue.get(countKey);
            long previousTime = lastValue.get(timeKey);

            lastValue.put(countKey, currentCount);
            lastValue.put(timeKey, currentTime);

            metrics.addField(countKey, currentCount - previousCount);
            metrics.addField(timeKey, currentTime - previousTime);
        }
    }

    public static void main(String[] args) {
    }
}
