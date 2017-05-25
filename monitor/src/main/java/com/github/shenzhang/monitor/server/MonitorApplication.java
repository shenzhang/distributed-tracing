package com.github.shenzhang.monitor.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:application.yml"})
public class MonitorApplication {
    @Autowired
    private Environment environment;

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(MonitorApplication.class);
        application.run(args);
    }
}
