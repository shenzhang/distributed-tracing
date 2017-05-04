package com.github.shenzhang.tracing.monitor.dao;

import com.github.shenzhang.tracing.monitor.domain.Span;
import com.github.shenzhang.tracing.monitor.exception.DaoException;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 4:26 PM.
 */
public interface SpanDao {
    void addSpan(List<Span> spans) throws DaoException;

    List<Span> getSpanByTraceId(String traceId) throws DaoException;
}
