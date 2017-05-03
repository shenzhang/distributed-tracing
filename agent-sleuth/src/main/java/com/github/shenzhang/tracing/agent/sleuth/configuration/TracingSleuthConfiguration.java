package com.github.shenzhang.tracing.agent.sleuth.configuration;

import com.github.shenzhang.tracing.agent.sleuth.reporter.SleuthSpanReporter;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:56 PM.
 */
@Configuration
public class TracingSleuthConfiguration {
    @Bean
    public SpanReporter reporter() {
        return new SleuthSpanReporter();
    }
}
