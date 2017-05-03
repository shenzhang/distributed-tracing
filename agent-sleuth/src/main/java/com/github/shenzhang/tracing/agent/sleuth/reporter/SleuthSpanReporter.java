package com.github.shenzhang.tracing.agent.sleuth.reporter;

import com.github.shenzhang.tracing.agent.forwarder.SpanForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanReporter;

/**
 * User: Zhang Shen
 * Date: 4/29/17
 * Time: 11:57 PM.
 */
public class SleuthSpanReporter implements SpanReporter {
    @Autowired
    private SpanForwarder forwarder;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void report(Span span) {
        com.github.shenzhang.tracing.agent.domain.Span mySpan = new com.github.shenzhang.tracing.agent.domain.Span();
        mySpan.setId(Span.idToHex(span.getSpanId()));
        if (span.getParents() != null && !span.getParents().isEmpty()) {
            mySpan.setParentId(Span.idToHex(span.getParents().get(0)));
        }
        mySpan.setTraceId(Span.idToHex(span.getTraceId()));

        mySpan.setSource(applicationName);
        mySpan.setBegin(span.getBegin());
        mySpan.setEnd(span.getEnd());
        mySpan.setDuration(span.getEnd() - span.getBegin());

        forwarder.forward(mySpan);
    }
}
