package com.github.shenzhang.dao;

import com.github.shenzhang.domain.Span;
import com.github.shenzhang.exception.DaoException;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/2/17
 * Time: 4:26 PM.
 */
public interface SpanDao {
    void addSpan(List<Span> spans) throws DaoException;
}
