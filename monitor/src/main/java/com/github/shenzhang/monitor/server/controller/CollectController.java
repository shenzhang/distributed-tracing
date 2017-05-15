package com.github.shenzhang.monitor.server.controller;

import com.github.shenzhang.monitor.server.controller.dto.collect.SpanDtoTranslator;
import com.github.shenzhang.monitor.server.service.CollectSpanService;
import com.github.shenzhang.monitor.server.controller.dto.collect.SpanDto;
import com.github.shenzhang.monitor.server.domain.Span;
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
    private SpanDtoTranslator translator = new SpanDtoTranslator();

    @RequestMapping(value = "/spans", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void collectSpans(@RequestBody SpanDto[] spans) {
        for (SpanDto spanDto : spans) {
            Span span = translator.toSpan(spanDto);
            spanService.addSpan(span);
        }
    }
}
