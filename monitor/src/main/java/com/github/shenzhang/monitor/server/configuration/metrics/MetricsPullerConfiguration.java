package com.github.shenzhang.monitor.server.configuration.metrics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 10:10 PM.
 */
@Configuration
public class MetricsPullerConfiguration {
    @Bean
    public static MetricsPullerProperties metricsPullerProperties() {
        return new MetricsPullerProperties();
    }
}
