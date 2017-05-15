package com.github.shenzhang.monitor.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: Zhang Shen
 * Date: 5/8/17
 * Time: 1:53 PM.
 */
@Configuration
public class DaoConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
