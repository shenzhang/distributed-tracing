package com.github.shenzhang.monitor.agent.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 3:45 PM.
 */
@Configuration
@EnableScheduling
public class MonitorAgentConfiguration {
    @Bean
    public static MonitorAgentProperties properties() {
        return new MonitorAgentProperties();
    }
}
