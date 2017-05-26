package com.github.shenzhang.monitor.agent.configuration.metrics;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.metrics.CountMetricsStore;
import com.github.shenzhang.monitor.agent.metrics.collector.ApplicationMetricsCollector;
import com.github.shenzhang.monitor.agent.metrics.collector.MetricsCollector;
import com.github.shenzhang.monitor.agent.metrics.collector.SystemMetricsCollector;
import com.github.shenzhang.monitor.agent.reporter.MetricsReporter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/26/17
 * Time: 6:17 PM.
 */
@Configuration
@ConditionalOnProperty(MonitorAgentProperties.Metrics.TOGGLE)
public class MetricsConfiguration {
    @Bean
    public MetricsReporter metricsReporter() {
        return new MetricsReporter();
    }

    @Bean
    public MetricsCollector systemMetricsCollector() {
        return new SystemMetricsCollector();
    }

    @Bean
    public MetricsCollector applicationMetricsCollector() {
        return new ApplicationMetricsCollector();
    }

    @Bean
    public CountMetricsStore countMetricsStore() {
        return new CountMetricsStore();
    }
}
