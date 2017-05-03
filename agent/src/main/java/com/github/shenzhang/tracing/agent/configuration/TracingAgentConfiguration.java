package com.github.shenzhang.tracing.agent.configuration;

import com.github.shenzhang.tracing.agent.forwarder.SpanForwarder;
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
    public SpanForwarder forwarder() {
        return new SpanForwarder();
    }
}
