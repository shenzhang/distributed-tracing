package com.github.shenzhang.monitor.agent.metrics.collector;

import com.github.shenzhang.monitor.agent.domain.Metrics;

/**
 * If API wants to extend more collector, just need to create a new collector which implements MetricsCollector
 * and add a instance to spring context. This new collector will be injected to the metrics collection process
 * automatically. <br/>
 *
 * User: Zhang Shen
 * Date: 5/17/17
 * Time: 2:56 PM.
 */
public interface MetricsCollector {
    Metrics collect();
}
