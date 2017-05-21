package com.github.shenzhang.monitor.agent.reporter;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.domain.Metrics;
import com.github.shenzhang.monitor.agent.metrics.collector.MetricsCollector;
import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/15/17
 * Time: 4:47 PM.
 */
@Component
public class MetricsReporter implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsReporter.class);

    @Autowired
    private MonitorAgentProperties properties;

    @Autowired
    private List<MetricsCollector> collectors;

    private HttpClient httpClient = HttpClientBuilder.create().build();

    private String host;

    @Value("${spring.application.name}")
    private String application;

    @Scheduled(fixedRate = 1000)
    public void report() {
        long now = new Date().getTime();

        List<Metrics> metricss = new ArrayList<>(collectors.size());
        for (MetricsCollector collector : collectors) {
            Metrics metrics = collector.collect();

            metrics.setTime(now);
            metrics.setApplication(application);
            metrics.setHost(host);

            metricss.add(metrics);
        }

        send(metricss);
    }

    private void send(List<Metrics> metricss) {
        Gson gson = new Gson();
        HttpPost post = new HttpPost(properties.getMetrics().getUrl());
        post.setEntity(new StringEntity(gson.toJson(metricss), ContentType.APPLICATION_JSON));


        try {
            int code = httpClient.execute(post).getStatusLine().getStatusCode();
            if (code != 201) {
                LOGGER.error("Send metrics failed - status code = ", code);
            }
        } catch (IOException e) {
            LOGGER.error("Send metrics failed - {}:{}", e.getClass().getName(), e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            this.host = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            this.host = this.application;
        }
    }
}
