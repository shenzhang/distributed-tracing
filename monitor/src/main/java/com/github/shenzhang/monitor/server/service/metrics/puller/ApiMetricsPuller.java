package com.github.shenzhang.monitor.server.service.metrics.puller;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shenzhang.monitor.server.domain.Application;
import com.github.shenzhang.monitor.server.domain.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 1:25 AM.
 */
public class ApiMetricsPuller extends MetricsPuller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMetricsPuller.class);
    private static final JsonPointer LOAD_POINTER = JsonPointer.valueOf("/systemload.average");
    private static final JsonPointer HEAP_POINTER = JsonPointer.valueOf("/heap");
    private static final JsonPointer USED_HEAP_POINTER = JsonPointer.valueOf("/heap.used");
    private static final int ERROR_LOG_INTERVAL_IN_SEC = 1;

    private Application application;
    private RestTemplate restTemplate;

    private Exception lastException;
    private LocalDateTime lastExceptionTime;

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
            List<Metrics> metricss = newArrayList();

            JsonNode loadNode = root.at(LOAD_POINTER);
            Metrics metrics = new Metrics(application);
            metrics.addFieldValue("load", loadNode.asDouble(0D));
            metricss.add(metrics);

            metrics = new Metrics(application);
            metrics.addFieldValue("heap", root.at(HEAP_POINTER).asInt(0) / 1024);
            metrics.addFieldValue("usedHeap", root.at(USED_HEAP_POINTER).asInt(0) / 1024);
            metricss.add(metrics);

            collect(metricss);
        } catch (Exception e) {
            logExceptionIfNecessary(metricsUrl, e);
        }
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private void logExceptionIfNecessary(String url, Exception e) {
        LocalDateTime now = LocalDateTime.now();
        if (lastException == null || !exceptionEquals(lastException, e)
                || Duration.between(lastExceptionTime, now).toMinutes() >= ERROR_LOG_INTERVAL_IN_SEC) {
            lastException = e;
            lastExceptionTime = now;
            LOGGER.error("Get metrics failed from {}, {}:{}", url, e.getClass().getName(), e.getMessage());
        }
    }

    private boolean exceptionEquals(Exception previous, Exception latest) {
        return previous.getClass() == latest.getClass() && Objects.equals(previous.getMessage(), latest.getMessage());

    }
}
