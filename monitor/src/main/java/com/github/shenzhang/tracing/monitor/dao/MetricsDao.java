package com.github.shenzhang.tracing.monitor.dao;

import com.github.shenzhang.tracing.monitor.domain.metrics.SystemMetrics;
import com.github.shenzhang.tracing.monitor.exception.DaoException;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/13/17
 * Time: 11:57 AM.
 */
public interface MetricsDao {
    void addSystemMetrics(List<SystemMetrics> metrics) throws DaoException;
}
