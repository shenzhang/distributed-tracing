package com.github.shenzhang.controller;

import com.github.shenzhang.controller.dto.SpanInRequest;
import com.github.shenzhang.domain.Span;
import com.github.shenzhang.service.CollectSpanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User: Zhang Shen
 * Date: 2/8/17
 * Time: 3:31 PM.
 */
@RestController
public class CollectController {
    @Autowired
    private CollectSpanService spanService;

    @RequestMapping(value = "/spans", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void collectSpans(@RequestBody SpanInRequest[] spans) {
        for (SpanInRequest spanInRequest : spans) {
            Span span = toSpan(spanInRequest);
            spanService.addSpan(span);
        }
    }

    private Span toSpan(SpanInRequest spanInRequest) {
        Span span = new Span();
        span.setId(spanInRequest.getId());
        span.setParentId(spanInRequest.getParentId());
        span.setSource(spanInRequest.getSource());
        span.setBegin(Long.parseLong(spanInRequest.getBegin()));
        span.setEnd(Long.parseLong(spanInRequest.getEnd()));
        span.setDuration(Integer.parseInt(spanInRequest.getDuration()));

        return span;
    }
}
