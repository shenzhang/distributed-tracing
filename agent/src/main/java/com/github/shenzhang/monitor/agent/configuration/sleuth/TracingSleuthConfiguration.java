package com.github.shenzhang.monitor.agent.configuration.sleuth;

import com.github.shenzhang.monitor.agent.reporter.sleuth.SleuthSpanReporter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:56 PM.
 */
@Configuration
@ConditionalOnClass({org.springframework.cloud.sleuth.SpanReporter.class})
public class TracingSleuthConfiguration {
    @Bean
    public SpanReporter reporter() {
        return new SleuthSpanReporter();
    }
}
