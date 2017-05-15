package com.github.shenzhang.monitor.server.dao;

import com.github.shenzhang.monitor.server.exception.DaoException;
import com.github.shenzhang.monitor.server.domain.Span;

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
