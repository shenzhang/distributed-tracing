package com.github.shenzhang.monitor.server.controller;

import com.github.shenzhang.monitor.server.controller.dto.collect.SpanDtoTranslator;
import com.github.shenzhang.monitor.server.service.TraceQueryService;
import com.github.shenzhang.monitor.server.controller.dto.collect.SpanDto;
import com.github.shenzhang.monitor.server.domain.Span;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 11:40 PM.
 */
@Controller
public class MonitorController {
    @Autowired
    private TraceQueryService traceQueryService;
    private SpanDtoTranslator translator = new SpanDtoTranslator();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/traces/{traceId}", method = RequestMethod.GET)
    @ResponseBody
    public List<SpanDto> querySpanByTrace(@PathVariable String traceId) {
        try {
            List<Span> spans = traceQueryService.querySpanByTraceId(traceId);
            return spans.stream().map(translator::fromSpan).collect(toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
