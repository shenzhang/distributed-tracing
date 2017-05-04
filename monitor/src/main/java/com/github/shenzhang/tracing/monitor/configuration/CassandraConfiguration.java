package com.github.shenzhang.tracing.monitor.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 11:58 PM.
 */
@Configuration
public class CassandraConfiguration implements ApplicationListener<ContextStoppedEvent> {
    @Autowired
    private TracingMonitorProperties properties;
    @Autowired
    private Session session;

    @Bean
    public Session session() {
        Cluster cluster = Cluster.builder().addContactPoints(properties.getCassandra().getServers()).build();
        return cluster.connect(properties.getCassandra().getKeyspace());
    }

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        if (!session.isClosed()) {
            session.close();
        }
    }
}
