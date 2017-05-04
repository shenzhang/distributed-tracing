package com.github.shenzhang.tracing.monitor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 10:10 PM.
 */
@Configuration
public class TracingMonitorConfiguration {
    @Bean
    public static TracingMonitorProperties properties() {
        return new TracingMonitorProperties();
    }
}
