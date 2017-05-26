package com.github.shenzhang.monitor.agent.configuration.tracing;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.reporter.DistributedTracingReporter;
import com.github.shenzhang.monitor.agent.reporter.sleuth.SleuthSpanReporter;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:56 PM.
 */
@Configuration
public class TracingConfiguration {
    @Bean
    @ConditionalOnClass({org.springframework.cloud.sleuth.SpanReporter.class})
    public SpanReporter sleuthReporter() {
        return new SleuthSpanReporter();
    }

    @Bean
    @ConditionalOnProperty(MonitorAgentProperties.Tracing.TOGGLE)
    public DistributedTracingReporter tracingReporter() {
        return new DistributedTracingReporter();
    }

    @Bean
    public TracingRepository repository() {
        return new TracingRepository();
    }
}
