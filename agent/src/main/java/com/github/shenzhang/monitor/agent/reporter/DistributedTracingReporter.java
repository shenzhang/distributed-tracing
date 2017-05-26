package com.github.shenzhang.monitor.agent.reporter;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.domain.Span;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import com.github.shenzhang.monitor.agent.util.HttpClientUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/3/17
 * Time: 12:36 AM.
 */
public class DistributedTracingReporter {
    private static Logger LOGGER = LoggerFactory.getLogger(DistributedTracingReporter.class);

    @Autowired
    private TracingRepository repository;

    @Autowired
    private MonitorAgentProperties properties;

    @Autowired
    @Qualifier("monitorAgentHttpClient")
    private HttpClient httpClient;

    @Scheduled(fixedRate = 5000)
    public void report() {
        List<Span> spans = repository.get();
        if (spans.isEmpty()) {
            return;
        }

        Gson gson = new Gson();
        HttpPost post = new HttpPost(properties.getTracing().getUrl());
        post.setEntity(new StringEntity(gson.toJson(spans), ContentType.APPLICATION_JSON));

        HttpResponse response = null;
        try {
            response = httpClient.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code != 201) {
                LOGGER.error("Send distributed-tracing span failed - status code = {}", code);
            }
        } catch (IOException e) {
            LOGGER.error("Send distributed-tracing span failed - {}:{}", e.getClass().getName(), e.getMessage());
        } finally {
            if (response != null) {
                HttpClientUtils.consumeEntity(response.getEntity());
            }
        }
    }
}
