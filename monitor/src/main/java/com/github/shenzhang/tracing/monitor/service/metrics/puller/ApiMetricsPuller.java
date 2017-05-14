package com.github.shenzhang.tracing.monitor.service.metrics.puller;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shenzhang.tracing.monitor.domain.Application;
import com.github.shenzhang.tracing.monitor.domain.metrics.SystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 1:25 AM.
 */
public class ApiMetricsPuller extends MetricsPuller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMetricsPuller.class);
    private static final JsonPointer LOAD_POINTER = JsonPointer.valueOf("/systemload.average");

    private Application application;
    private RestTemplate restTemplate;

    public ApiMetricsPuller(Application application) {
        this.application = application;
    }

    @Override
    public void run() {
        String metricsUrl = application.getUrl() + "metrics";
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(metricsUrl, String.class);
            String body = responseEntity.getBody();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(body);

            Date now = new Date();
            JsonNode loadNode = root.at(LOAD_POINTER);
            if (!loadNode.isMissingNode()) {
                SystemMetrics systemMetrics = new SystemMetrics();
                systemMetrics.init(application);
                systemMetrics.setLoad(loadNode.asDouble());

                collect(newArrayList(systemMetrics));

            }
        } catch (Exception e) {
            LOGGER.error("Get metrics failed from {}, {}:{}", metricsUrl, e.getClass().getName(), e.getMessage());
        }

    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
