package com.github.shenzhang.configuration;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:43 PM.
 */
@Configuration
public class SleuthConfiguration {
    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }
}
