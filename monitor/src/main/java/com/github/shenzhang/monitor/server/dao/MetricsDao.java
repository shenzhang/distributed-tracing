package com.github.shenzhang.monitor.server.dao;

import com.github.shenzhang.monitor.server.domain.metrics.JvmMetrics;
import com.github.shenzhang.monitor.server.domain.metrics.SystemMetrics;
import com.github.shenzhang.monitor.server.exception.DaoException;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/13/17
 * Time: 11:57 AM.
 */
public interface MetricsDao {
    void addSystemMetrics(List<SystemMetrics> metrics) throws DaoException;

    void addJvmMetrics(List<JvmMetrics> metrics) throws DaoException;
}
