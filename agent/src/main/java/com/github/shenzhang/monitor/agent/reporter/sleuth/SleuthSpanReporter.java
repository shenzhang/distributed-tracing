package com.github.shenzhang.monitor.agent.reporter.sleuth;

import com.github.shenzhang.monitor.agent.configuration.MonitorAgentProperties;
import com.github.shenzhang.monitor.agent.tracing.TracingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;

/**
 * Collect all the spans and report them to DistributedTracingReporter
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:57 PM.
 */
public class SleuthSpanReporter implements org.springframework.cloud.sleuth.SpanReporter {
    @Autowired
    private MonitorAgentProperties properties;
    @Autowired
    private TracingRepository repository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void report(Span span) {
        if (!properties.getTracing().isEnabled()) {
            return;
        }

        com.github.shenzhang.monitor.agent.domain.Span mySpan = new com.github.shenzhang.monitor.agent.domain.Span();
        mySpan.setId(Span.idToHex(span.getSpanId()));
        if (span.getParents() != null && !span.getParents().isEmpty()) {
            mySpan.setParentId(Span.idToHex(span.getParents().get(0)));
        }

        mySpan.setTraceId(Span.idToHex(span.getTraceId()));
        mySpan.setName(span.getName());
        mySpan.setSource(applicationName);
        mySpan.setBeginTime(span.getBegin());
        mySpan.setEndTime(span.getEnd());
        mySpan.setDuration(span.getEnd() - span.getBegin());
        mySpan.setSuccess(!span.tags().containsKey(Span.SPAN_ERROR_TAG_NAME));

        repository.add(mySpan);
    }
}
