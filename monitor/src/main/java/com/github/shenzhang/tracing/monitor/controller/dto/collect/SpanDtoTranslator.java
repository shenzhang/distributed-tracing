package com.github.shenzhang.tracing.monitor.controller.dto.collect;

import com.github.shenzhang.tracing.monitor.domain.Span;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 11:21 PM.
 */
public class SpanDtoTranslator {
    public Span toSpan(SpanDto spanDto) {
        Span span = new Span();
        span.setId(spanDto.getId());
        span.setParentId(spanDto.getParentId());
        span.setTraceId(spanDto.getTraceId());
        span.setSource(spanDto.getSource());
        span.setBegin(Long.parseLong(spanDto.getBegin()));
        span.setEnd(Long.parseLong(spanDto.getEnd()));
        span.setDuration(Integer.parseInt(spanDto.getDuration()));

        return span;
    }

    public SpanDto fromSpan(Span span) {
        SpanDto spanDto = new SpanDto();

        spanDto.setId(span.getId());
        spanDto.setParentId(span.getParentId());
        spanDto.setTraceId(span.getTraceId());
        spanDto.setSource(span.getSource());
        spanDto.setBegin(Long.toString(span.getBegin()));
        spanDto.setEnd(Long.toString(span.getEnd()));
        spanDto.setDuration(Integer.toString(span.getDuration()));

        return spanDto;
    }
}
