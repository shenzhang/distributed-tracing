package com.github.shenzhang.monitor.server.controller.dto.collect;

import com.github.shenzhang.monitor.server.domain.Span;

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
        span.setName(spanDto.getName());
        span.setSource(spanDto.getSource());
        span.setBegin(spanDto.getBeginTime());
        span.setEnd(spanDto.getEndTime());
        span.setDuration(Integer.parseInt(spanDto.getDuration()));
        span.setSuccess(spanDto.isSuccess());

        return span;
    }

    public SpanDto fromSpan(Span span) {
        SpanDto spanDto = new SpanDto();

        spanDto.setId(span.getId());
        spanDto.setParentId(span.getParentId());
        spanDto.setTraceId(span.getTraceId());
        spanDto.setName(span.getName());
        spanDto.setSource(span.getSource());
        spanDto.setBeginTime(span.getBegin());
        spanDto.setEndTime(span.getEnd());
        spanDto.setDuration(Integer.toString(span.getDuration()));
        spanDto.setSuccess(span.isSuccess());

        return spanDto;
    }
}
