package com.github.shenzhang.monitor.agent.configuration;

import com.github.shenzhang.monitor.agent.reporter.DistributedTracingReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:45 PM.
 */
@Configuration
public class TracingAgentConfiguration {
    @Bean
    public static TracingAgentProperties properties() {
        return new TracingAgentProperties();
    }

    @Bean
    public DistributedTracingReporter forwarder() {
        return new DistributedTracingReporter();
    }
}
