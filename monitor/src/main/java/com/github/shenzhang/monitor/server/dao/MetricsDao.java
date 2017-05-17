package com.github.shenzhang.monitor.server.dao;

import com.github.shenzhang.monitor.server.domain.Metrics;
import com.github.shenzhang.monitor.server.exception.DaoException;

import java.util.List;

/**
 * User: Zhang Shen
 * Date: 5/13/17
 * Time: 11:57 AM.
 */
public interface MetricsDao {
    void addMetrics(List<Metrics> metricss) throws DaoException;
}
