package com.github.shenzhang.monitor.server.domain.metrics;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public class SystemMetrics extends Metrics {
    private double load;

    @Override
    public String getMeasurement() {
        return "system";
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }
}
