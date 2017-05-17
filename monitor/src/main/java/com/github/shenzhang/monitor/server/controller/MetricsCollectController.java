package com.github.shenzhang.monitor.server.controller;

import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.service.MetricsCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Zhang Shen
 * Date: 5/15/17
 * Time: 9:23 PM.
 */
@RestController
public class MetricsCollectController {
    @Autowired
    private MetricsCollector collector;

    @RequestMapping(value = "/all-metrics", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void collectSpans(@RequestBody Metrics[] metricss) {
        collector.collect(newArrayList(metricss));
    }
}
