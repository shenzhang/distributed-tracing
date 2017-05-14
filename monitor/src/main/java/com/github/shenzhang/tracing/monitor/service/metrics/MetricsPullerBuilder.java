package com.github.shenzhang.tracing.monitor.service.metrics;

import com.github.shenzhang.tracing.monitor.domain.Application;
import com.github.shenzhang.tracing.monitor.service.metrics.puller.ApiMetricsPuller;
import com.github.shenzhang.tracing.monitor.service.metrics.puller.MetricsPuller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 1:24 AM.
 */
@Component
public class MetricsPullerBuilder {
    @Autowired
    private MetricsCollector metricsCollector;

    public MetricsPuller build(Application application) {
        ApiMetricsPuller puller = new ApiMetricsPuller(application);
        puller.setMetricsCollector(metricsCollector);

        RestTemplate restTemplate = new RestTemplate();
        puller.setRestTemplate(restTemplate);

        return puller;
    }
}
