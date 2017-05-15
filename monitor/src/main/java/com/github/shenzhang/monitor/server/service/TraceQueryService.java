package com.github.shenzhang.monitor.server.service;

import com.github.shenzhang.monitor.server.dao.SpanDao;
import com.github.shenzhang.monitor.server.domain.Span;
import com.github.shenzhang.monitor.server.exception.DaoException;
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
