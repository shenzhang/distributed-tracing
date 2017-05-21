package com.github.shenzhang.monitor.agent.metrics.collector;

import com.github.shenzhang.monitor.agent.domain.Metrics;

/**
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 2:56 PM.
 */
public interface MetricsCollector {
    Metrics collect();
}
