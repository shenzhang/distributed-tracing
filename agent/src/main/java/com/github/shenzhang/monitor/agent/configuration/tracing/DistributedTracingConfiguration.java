package com.github.shenzhang.monitor.agent.configuration.tracing;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:45 PM.
 */
@Configuration
public class DistributedTracingConfiguration {
    @Bean
    public static MonitorAgentProperties properties() {
        return new MonitorAgentProperties();
    }
}
