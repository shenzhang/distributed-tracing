package com.github.shenzhang.tracing.monitor.domain.metrics;

/**
 * User: Zhang Shen
 * Date: 5/14/17
 * Time: 12:33 AM.
 */
public class CpuMetrics extends Metrics {
    private int idle;
    private int user;
    private int system;

    @Override
    public String getMeasurement() {
        return "cpu";
    }

    public int getIdle() {
        return idle;
    }

    public void setIdle(int idle) {
        this.idle = idle;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

}
