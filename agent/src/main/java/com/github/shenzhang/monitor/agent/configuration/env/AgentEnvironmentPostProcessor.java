package com.github.shenzhang.monitor.agent.configuration.env;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * User: Zhang Shen
 * Date: 5/26/17
 * Time: 6:11 PM.
 */
public class AgentEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String enableTracing = environment.getProperty(MonitorAgentProperties.Tracing.TOGGLE);
        if (enableTracing == null || !enableTracing.toLowerCase().equals("true")) {
//            System.setProperty("spring.sleuth.enabled", "false");
            System.setProperty("spring.sleuth.sampler.percentage", "0");
        }
    }
}
