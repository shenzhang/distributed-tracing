package com.github.shenzhang.tracing.monitor.service;

import com.github.shenzhang.tracing.monitor.dao.SpanDao;
import com.github.shenzhang.tracing.monitor.domain.Span;
import com.github.shenzhang.tracing.monitor.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/4/17
 * Time: 11:17 PM.
 */
@Service
public class TraceQueryService {
    @Autowired
    private SpanDao spanDao;

    public List<Span> querySpanByTraceId(String traceId) throws Exception {
        List<Span> spans;

        try {
            spans = spanDao.getSpanByTraceId(traceId);
        } catch (DaoException e) {
            throw new Exception(e);
        }

        return spans;
    }
}
