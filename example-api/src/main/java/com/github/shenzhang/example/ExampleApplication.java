package com.github.shenzhang.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * User: shenzhang
 * Date: 3/27/14
 * Time: 2:13 PM
 */

@SpringBootApplication
@PropertySource({"classpath:application.yml"})
public class ExampleApplication {
    @Autowired
    private Environment environment;

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(ExampleApplication.class);
        application.run(args);
    }
}
